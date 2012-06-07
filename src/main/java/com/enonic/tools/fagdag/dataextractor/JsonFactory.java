package com.enonic.tools.fagdag.dataextractor;

import org.json.JSONObject;

public class JsonFactory
{

    public static String getMetaDataJson( TimeLogEntry entry )
    {
        return "{ \"index\" : { \"_index\" : \"test\", \"_type\" : \"type1\", \"_id\" : \"" + entry.getKey() + "\" } } ";
    }

    public static String buildAsJson( TimeLogEntry entry )
    {
        JSONObject jsonObject = new JSONObject( entry );
        return jsonObject.toString();
    }


}

