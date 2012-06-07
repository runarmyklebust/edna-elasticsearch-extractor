package com.enonic.tools.fagdag.dataextractor;


public class ElasticsearchDataExtractor
{
    private final static int DEFAULT_BATCH_SIZE = 100;

    private final static int DEFAULT_MAX_NUMBER = 500;


    public static void main( String[] args )
    {

        final Integer batchSize = args[0] != null ? new Integer( args[0] ) : DEFAULT_BATCH_SIZE;
        final Integer maxNumber = args[1] != null ? new Integer( args[1] ) : DEFAULT_MAX_NUMBER;

        ResourceResolver resourceResolver = new ResourceResolver();
        CustomerResolver customerResolver = new CustomerResolver();

        DataExtractorWorker dataExtractorWorker = new DataExtractorWorker( resourceResolver, customerResolver );

        int currentStart = 0;

        while ( currentStart < maxNumber )
        {
            System.out.println( "Processing from " + currentStart + " to " + ( batchSize - 1 ) + "......" );

            dataExtractorWorker.extractData( currentStart, batchSize );
            currentStart = currentStart + batchSize;
        }
    }

}
