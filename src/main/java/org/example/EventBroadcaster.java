package org.example;

import org.cometd.bayeux.server.BayeuxServer;
import org.cometd.bayeux.server.LocalSession;
import org.cometd.bayeux.server.ServerChannel;

import java.util.HashMap;
import java.util.Map;


public class EventBroadcaster {
    public static final String CHANNEL_ID = "/useractivity";
    public static final String USER_EVENT = "userevent";
    private final BayeuxServer server;
    private LocalSession session;
    private EventBroadcaster instance = null;

    public  EventBroadcaster (BayeuxServer server) {
        this.server = server;
        this.session = server.newLocalSession("sender");
        session.handshake();
        server.createIfAbsent(CHANNEL_ID);
    }

    public void userAdded(String userName) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put(USER_EVENT, userName + " added");
        broadcastEvent(CHANNEL_ID, data);
    }

    public void userRemoved(String userName) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put(USER_EVENT, userName + " removed");
        broadcastEvent(CHANNEL_ID, data);
    }

    private void broadcastEvent(String channelId, Map<String, Object> data) {
        ServerChannel sc = server.getChannel(channelId);
        if  (sc != null) {
            sc.publish(session, data, null);
        }
    }

}
