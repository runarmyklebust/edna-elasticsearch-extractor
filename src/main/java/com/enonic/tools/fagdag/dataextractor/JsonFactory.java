package com.enonic.tools.fagdag.dataextractor;

import org.json.JSONObject;

public class JsonFactory
{

    public static String getMetaDataJson( String indexName, TimeLogEntry entry )
    {
        return "{ \"index\" : { \"_index\" : \"" + indexName + "\", \"_type\" : \"edna\", \"_id\" : \"" + entry.getKey() + "\" } } ";
    }

    public static String buildAsJson( TimeLogEntry entry )
    {
        JSONObject jsonObject = new JSONObject( entry );
        return jsonObject.toString();
    }


}

