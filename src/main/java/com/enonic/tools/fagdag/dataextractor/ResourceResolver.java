package com.enonic.tools.fagdag.dataextractor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;

import com.enonic.cms.api.client.Client;

public class ResourceResolver
{
    private Map<String, String> resourceMap = new HashMap<String, String>();

    public ResourceResolver()
    {
        final Document resourceDocument = getResourceDocument();
        populateResourceMap( resourceDocument.getRootElement() );
    }

    private Document getResourceDocument()
    {
        Client client = ClientAccessor.getClient();

        final Document contentDoc = TimeLogDataFetcher.getResourceDocument( client );

        return contentDoc;
    }

    private void populateResourceMap( Element resourceDocRoot )
    {
        Iterator itr = ( resourceDocRoot.getChildren() ).iterator();
        while ( itr.hasNext() )
        {
            Element content = (Element) itr.next();

            final Element contentdataEl = content.getChild( "contentdata" );

            if ( contentdataEl == null )
            {
                continue;
            }

            final Attribute resourceKey = content.getAttribute( "key" );

            final Element nameEl = contentdataEl.getChild( "code" );

            resourceMap.put( resourceKey.getValue(), nameEl.getValue() );
        }
    }


    public String getResourceName( final String resourceKey )
    {
        String resourceName = resourceMap.get( resourceKey );

        if ( resourceName == null )
        {
            resourceName = "UNKNOWN";
        }

        return resourceName;
    }

}
