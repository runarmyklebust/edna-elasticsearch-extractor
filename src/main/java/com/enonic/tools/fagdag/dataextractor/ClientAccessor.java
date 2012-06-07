package com.enonic.tools.fagdag.dataextractor;

import com.enonic.cms.api.client.Client;
import com.enonic.cms.api.client.ClientFactory;

public class ClientAccessor
{
    private static Client client;


    public static Client getClient()
    {
        if ( client == null )
        {
            client = ClientFactory.getRemoteClient( "http://vtnode2:8080/cms-46ce-unstable-enonic/rpc/bin" );
            client.login( "admin", "password" );
        }

        return client;
    }


}
