package com.enonic.tools.fagdag.dataextractor;

import java.text.SimpleDateFormat;
import java.util.Date;


public class TimeLogEntry
{
    private String key;

    private Double hours;

    private String resource;

    private boolean billable;

    private String logDate;

    private String description;

    private String customer;

    private String project;

    public final static SimpleDateFormat elasticsearchFullDateFormat = new SimpleDateFormat( "yyyy/MM/dd HH:mm:ss" );


    public void setCustomer( final String customer )
    {
        this.customer = customer;
    }

    public void setDescription( final String description )
    {
        this.description = description;
    }


    public String getKey()
    {
        return key;
    }

    public void setKey( final String key )
    {
        this.key = key;
    }


    public void setResource( final String resource )
    {
        this.resource = resource;
    }

    public void setBillable( final boolean billable )
    {
        this.billable = billable;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        builder.append(
            "Key:" + key + ", hours:" + hours + ", isBillable:" + billable + ", resourceKey:" + resource + ", customer:" + customer +
                ", date:" + logDate + ", description: " + description );

        return builder.toString();
    }

    public void setHours( final String hours )
    {
        this.hours = new Double( hours );
    }

    public Double getHours()
    {
        return hours;
    }

    public void setHours( final Double hours )
    {
        this.hours = hours;
    }

    public String getResource()
    {
        return resource;
    }

    public boolean isBillable()
    {
        return billable;
    }

    public String getLogDate()
    {
        return logDate;
    }

    public void setLogDate( final Date logDate )
    {
        this.logDate = elasticsearchFullDateFormat.format( logDate );
    }

    public void setLogDate( final String logDate )
    {
        this.logDate = logDate;
    }

    public String getDescription()
    {
        return description;
    }

    public String getCustomer()
    {
        return customer;
    }

    public String getProject()
    {
        return project;
    }

    public void setProject( final String project )
    {
        this.project = project;
    }
}

