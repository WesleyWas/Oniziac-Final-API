package com.weswaas.api.utils.server_pinging;

import java.util.List;

/**
 * Created by Weswas on 10/01/2017.
 */
public class StatusResponse {

    public String description;
    public Players players;
    public Version version;
    public String favicon;
    public int time;

    public StatusResponse()
    {
        this.players = new Players();
        this.version = new Version();
    }

    public class Version
    {
        public String name;
        public String protocol;

        public Version() {}
    }

    public class StatusPlayer
    {
        public String name;
        public String id;

        public StatusPlayer() {}

        public String toString()
        {
            return "{Player:[" + this.name + "," + this.id + "]}";
        }
    }

    public class Players
    {
        public int max;
        public int online;
        public List<StatusPlayer> sample;

        public Players() {}
    }

}
