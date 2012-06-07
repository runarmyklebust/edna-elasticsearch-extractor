package com.enonic.tools.fagdag.dataextractor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jdom.Attribute;
import org.jdom.Element;

public class TimeLogEntryFactory
{
    private final static SimpleDateFormat logDateFormat = new SimpleDateFormat( "yyyy-MM-dd" );

    private final ResourceResolver resourceResolver;

    private final CustomerResolver customerResolver;

    public TimeLogEntryFactory( final ResourceResolver resourceResolver, final CustomerResolver customerResolver )
    {
        this.resourceResolver = resourceResolver;
        this.customerResolver = customerResolver;
    }

    public TimeLogEntry buildTimeLogEntry( Element logEntry )
    {
        TimeLogEntry entry = new TimeLogEntry();

        entry.setKey( getAttribute( logEntry, "key" ) );

        final Element contentDataEl = getChildElement( logEntry, "contentdata" );

        if ( contentDataEl == null )
        {
            return null;
        }

        entry.setHours( getChildElementValue( contentDataEl, "hours" ) );
        entry.setBillable( getChildElementValueAsBoolean( contentDataEl, "hours" ) );

        final String resourceKey = getChildAttribute( contentDataEl, "resource", "key" );
        entry.setResource( resourceResolver.getResourceName( resourceKey ) );

        final String activityKey = getChildAttribute( contentDataEl, "activity", "key" );
        entry.setCustomer( customerResolver.getCustomerName( activityKey ) );
        entry.setProject( customerResolver.getProjectName( activityKey ) );

        entry.setLogDate( getChildElementValueAsDate( contentDataEl, "date" ) );
        entry.setDescription( getChildElementValue( contentDataEl, "description" ) );

        return entry;
    }


    private String getChildAttribute( Element parent, String childName, String attributeName )
    {
        final Element childElement = getChildElement( parent, childName );
        if ( childElement == null )
        {
            return "";
        }

        return getAttribute( childElement, attributeName );
    }

    private String getAttribute( Element element, String attributeName )
    {
        final Attribute attribute = element.getAttribute( attributeName );

        if ( attribute == null )
        {
            return "";
        }

        return attribute.getValue();
    }

    private String getChildElementValue( Element parent, String childName )
    {
        final Element childElement = getChildElement( parent, childName );

        if ( childElement == null )
        {
            return "";
        }

        return childElement.getValue();
    }

    private Element getChildElement( final Element parent, final String childName )
    {
        return parent.getChild( childName );
    }


    private boolean getChildElementValueAsBoolean( Element parent, String childName )
    {
        final Element childElement = getChildElement( parent, childName );

        if ( childElement == null )
        {
            return false;
        }

        return Boolean.valueOf( childElement.getValue() );
    }

    private Date getChildElementValueAsDate( Element parent, String childName )
    {
        final Element childElement = getChildElement( parent, childName );

        if ( childElement == null )
        {
            return null;
        }

        try
        {
            final Date logDate = logDateFormat.parse( childElement.getValue() );
            return logDate;
        }
        catch ( ParseException e )
        {
            return null;
        }

    }


}
