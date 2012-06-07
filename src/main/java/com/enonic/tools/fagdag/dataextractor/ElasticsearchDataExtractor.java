package com.enonic.tools.fagdag.dataextractor;


public class ElasticsearchDataExtractor
{

    public static void main( String[] args )
    {

        TimeLogDataFetcher dataFetcher = new TimeLogDataFetcher();

        ResourceResolver resourceResolver = new ResourceResolver();
        CustomerResolver customerResolver = new CustomerResolver();

        DataExtractorWorker dataExtractorWorker = new DataExtractorWorker( resourceResolver, customerResolver );

        int batchSize = 10000;
        int maxElements = 50000;
        int currentStart = 0;

        while ( currentStart < maxElements )
        {
            dataExtractorWorker.extractData( currentStart, batchSize );
            currentStart = currentStart + batchSize;
        }
    }

}
