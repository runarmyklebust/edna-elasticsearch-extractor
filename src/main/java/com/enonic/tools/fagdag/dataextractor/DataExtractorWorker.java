package com.enonic.tools.fagdag.dataextractor;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;

public class DataExtractorWorker
{
    private final ResourceResolver resourceResolver;

    private final CustomerResolver customerResolver;

    private final String indexName;

    public DataExtractorWorker( final String indexName, final ResourceResolver resourceResolver, final CustomerResolver customerResolver )
    {
        this.indexName = indexName;
        this.resourceResolver = resourceResolver;
        this.customerResolver = customerResolver;
    }

    public void extractData( int index, int count )
    {
        TimeLogDataFetcher dataFetcher = new TimeLogDataFetcher();
        Document contentDoc = dataFetcher.getTimeLogDocument( index, count );

        final TimeLogEntryFactory timeLogEntryFactory = new TimeLogEntryFactory( resourceResolver, customerResolver );

        final List<TimeLogEntry> timeLogEntries = createTimeLogEntries( contentDoc, timeLogEntryFactory );

        writeAsJsonToFile( timeLogEntries, createFileName( index, count ), indexName );
    }

    private String createFileName( final int index, final int count )
    {
        return "timeLogDataImport_" + index + "-" + ( index + count - 1 ) + ".json";
    }

    private List<TimeLogEntry> createTimeLogEntries( Document contentDoc, TimeLogEntryFactory timeLogEntryFactory )
    {
        List<TimeLogEntry> timeLogEntries = new ArrayList<TimeLogEntry>();

        Element contentsEl = contentDoc.getRootElement();

        Iterator itr = ( contentsEl.getChildren() ).iterator();
        while ( itr.hasNext() )
        {
            Element content = (Element) itr.next();
            final TimeLogEntry timeLogEntry = timeLogEntryFactory.buildTimeLogEntry( content );

            if ( timeLogEntry != null )
            {
                timeLogEntries.add( timeLogEntry );
            }
        }
        return timeLogEntries;
    }

    public void writeAsJsonToFile( List<TimeLogEntry> timeLogEntries, String fileName, String indexName )
    {
        BufferedWriter bufferedWriter = null;
        try
        {
            bufferedWriter = new BufferedWriter( new OutputStreamWriter( new FileOutputStream( fileName ), "UTF8" ) );

            for ( TimeLogEntry entry : timeLogEntries )
            {
                bufferedWriter.write( JsonFactory.getMetaDataJson( indexName, entry ) );
                bufferedWriter.newLine();
                bufferedWriter.write( JsonFactory.buildAsJson( entry ) );
                bufferedWriter.newLine();
            }
        }
        catch ( FileNotFoundException ex )
        {
            ex.printStackTrace();
        }
        catch ( IOException ex )
        {
            ex.printStackTrace();
        }
        finally
        {
            //Close the BufferedWriter
            try
            {
                if ( bufferedWriter != null )
                {
                    bufferedWriter.flush();
                    bufferedWriter.close();
                }
            }
            catch ( IOException ex )
            {
                ex.printStackTrace();
            }
        }


    }

}
