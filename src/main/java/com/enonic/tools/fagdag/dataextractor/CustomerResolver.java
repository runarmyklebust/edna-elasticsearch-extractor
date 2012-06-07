package com.enonic.tools.fagdag.dataextractor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;

import com.enonic.cms.api.client.Client;

public class CustomerResolver
{
    private Map<String, Activity> activityMap = new HashMap<String, Activity>();

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

            final Element projectNameEl = contentdataEl.getChild( "project_name" );

            if ( nameEl == null || projectNameEl == null )
            {
                continue;
            }

            activityMap.put( resourceKey.getValue(), new Activity( nameEl.getValue(), projectNameEl.getValue() ) );
        }
    }

    public String getCustomerName( final String activityKey )
    {
        Activity activity = activityMap.get( activityKey );

        if ( activity == null )
        {
            return "UNKNOWN";
        }

        return activity.getCustomerName();
    }

    public String getProjectName( final String activityKey )
    {
        Activity activity = activityMap.get( activityKey );

        if ( activity == null )
        {
            return "UNKNOWN";
        }

        return activity.getProjectName();
    }

    private class Activity
    {
        private String customerName;

        private String projectName;

        private Activity( final String customerName, final String projectName )
        {
            this.customerName = customerName;
            this.projectName = projectName;
        }

        public String getCustomerName()
        {
            if ( StringUtils.isBlank( this.customerName ) )
            {
                return "UNKNOWN";
            }

            return customerName;
        }

        public String getProjectName()
        {
            if ( StringUtils.isBlank( this.projectName ) )
            {
                return "UNKNOWN";
            }

            return projectName;
        }
    }


}
