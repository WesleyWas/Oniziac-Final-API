package com.weswaas.api.utils.server_pinging;

/**
 * Created by Weswas on 10/01/2017.
 */
public abstract class ServerPinger {

    public static final ServerPinger POST_NETTY_REWRITE = new ServerPinger3();

    public abstract StatusResponse fetchData(String paramString, int paramInt);

}
