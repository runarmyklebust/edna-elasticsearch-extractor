package com.enonic.tools.fagdag.dataextractor;

import java.util.Date;

public class TimeLogEntry
{
    private String key;

    private String hours;

    private String resource;

    private boolean billable;

    private Date logDate;

    private String description;

    private String customer;

    public void setCustomer( final String customer )
    {
        this.customer = customer;
    }

    public void setDescription( final String description )
    {
        this.description = description;
    }

    public void setLogDate( final Date logDate )
    {
        this.logDate = logDate;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey( final String key )
    {
        this.key = key;
    }

    public void setHours( final String hours )
    {
        this.hours = hours;
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


    public String getHours()
    {
        return hours;
    }

    public String getResource()
    {
        return resource;
    }

    public boolean isBillable()
    {
        return billable;
    }

    public Date getLogDate()
    {
        return logDate;
    }

    public String getDescription()
    {
        return description;
    }

    public String getCustomer()
    {
        return customer;
    }
}

