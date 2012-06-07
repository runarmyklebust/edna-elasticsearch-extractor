package com.enonic.tools.fagdag.dataextractor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;

import com.enonic.cms.api.client.Client;

public class CustomerResolver
{
    private Map<String, String> activityMap = new HashMap<String, String>();

    public CustomerResolver()
    {
        final Document activityDocument = getActivityDocument();
        populateActivityMap( activityDocument.getRootElement() );
    }

    private Document getActivityDocument()
    {
        Client client = ClientAccessor.getClient();

        final Document activityDoc = TimeLogDataFetcher.getActivityDocument( client );

        return activityDoc;
    }

    public void populateActivityMap( Element activityDocRoot )
    {
        Iterator itr = ( activityDocRoot.getChildren() ).iterator();
        while ( itr.hasNext() )
        {
            Element content = (Element) itr.next();

            final Element contentdataEl = content.getChild( "contentdata" );

            if ( contentdataEl == null )
            {
                continue;
            }

            final Attribute resourceKey = content.getAttribute( "key" );

            final Element nameEl = contentdataEl.getChild( "customer_name" );

            if ( nameEl == null )
            {
                continue;
            }

            activityMap.put( resourceKey.getValue(), nameEl.getValue() );
        }
    }

    public String getCustomerName( final String activityKey )
    {
        String customerName = activityMap.get( activityKey );

        if ( customerName == null )
        {
            return "UNKNOWN";
        }

        return customerName;
    }


}
