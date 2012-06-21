package com.enonic.tools.fagdag.dataextractor;

import org.jdom.Document;

import com.enonic.cms.api.client.Client;
import com.enonic.cms.api.client.ClientFactory;
import com.enonic.cms.api.client.model.GetContentByCategoryParams;

public class TimeLogDataFetcher
{
    private Client client;

    public Document getTimeLogDocument( int index, int count )
    {
        Client client = getClient();

        final Document contentDoc = getContentDocument( client, index, count );

        return contentDoc;
    }


    public static Document getResourceDocument( final Client client )
    {
        GetContentByCategoryParams params = new GetContentByCategoryParams();
        params.categoryKeys = new int[]{1484};
        params.includeData = true;
        params.includeOfflineContent = false;
        params.includeVersionsInfo = false;
        params.count = 100;
        params.childrenLevel = 0;

        return client.getContentByCategory( params );
    }

    public static Document getActivityDocument( final Client client )
    {
        GetContentByCategoryParams params = new GetContentByCategoryParams();
        params.categoryKeys = new int[]{1482};
        params.includeData = true;
        params.includeOfflineContent = true;
        params.includeVersionsInfo = false;
        params.count = 10000;
        params.childrenLevel = 0;
        params.parentLevel = 0;

        return client.getContentByCategory( params );
    }


    private Document getContentDocument( final Client client, int index, int count )
    {
        GetContentByCategoryParams params = new GetContentByCategoryParams();
        params.categoryKeys = new int[]{1483};
        params.includeData = true;
        params.includeOfflineContent = false;
        params.includeVersionsInfo = false;
        params.count = count;
        params.index = index;

        return client.getContentByCategory( params );
    }

    private Client getClient()
    {
        if ( client == null )
        {
            client = ClientFactory.getRemoteClient( "http://vtnode2:8080/cms-46ce-unstable-enonic/rpc/bin" );
            client.login( "admin", "password" );
        }

        return client;
    }


}


