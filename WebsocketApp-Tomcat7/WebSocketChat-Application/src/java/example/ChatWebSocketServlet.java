/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package example;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;
import org.apache.catalina.websocket.WsOutbound;

/**
 * Example web socket servlet for chat.
 */
public class ChatWebSocketServlet extends WebSocketServlet {

//    http://n-chandra.blogspot.in/2012/02/gettting-started-with-html5-websockets.html
    private static final long serialVersionUID = 1L;

    private static final String GUEST_PREFIX = "Guest";

    private final AtomicInteger connectionIds = new AtomicInteger(0);
    private final Set<ChatMessageInbound> connections = new CopyOnWriteArraySet<ChatMessageInbound>();

    @Override
    protected StreamInbound createWebSocketInbound(String subProtocol,
            HttpServletRequest request) {
//        String chatParam = request.getParameter("chat");
//        System.out.println("ChatParam " + chatParam);
        return new ChatMessageInbound(connectionIds.incrementAndGet());
    }

    private final class ChatMessageInbound extends MessageInbound {

        private final String nickname;

        private ChatMessageInbound(int id) {
            Logger.getLogger(ChatWebSocketServlet.class.getName()).log(Level.ALL, null, "ChatMessageInbound method called ... ");
            System.out.println("ChatMessageInbound method called ... ");
            this.nickname = GUEST_PREFIX + id;
        }

        @Override
        protected void onOpen(WsOutbound outbound) {
            Logger.getLogger(ChatWebSocketServlet.class.getName()).log(Level.ALL, null, "onOpen method called ...  ");
            System.out.println("onOpen method called ... ");
            connections.add(this);
            String message = String.format("* %s %s", nickname, "has joined.");
            broadcast(message);
        }

        @Override
        protected void onClose(int status) {
            Logger.getLogger(ChatWebSocketServlet.class.getName()).log(Level.ALL, null, "onClose method called ... ");
            System.out.println("onClose method called ... ");
            connections.remove(this);
            String message = String.format("* %s %s", nickname, "has disconnected.");
            broadcast(message);
        }

        @Override
        protected void onBinaryMessage(ByteBuffer message) throws IOException {
            Logger.getLogger(ChatWebSocketServlet.class.getName()).log(Level.ALL, null, "onBinaryMessage method called ... ");
            System.out.println("onBinaryMessage method called ... ");
            throw new UnsupportedOperationException("Binary message not supported.");
        }

        @Override
        protected void onTextMessage(CharBuffer message) throws IOException {
            // Parses query string
//            UrlEncoded parameters = new UrlEncoded(queryString);
            Logger.getLogger(ChatWebSocketServlet.class.getName()).log(Level.ALL, null, "onTextMessage method called ... ");
            System.out.println("onTextMessage method called ... ");
            // Never trust the client
//            String filteredMessage = String.format("%s: %s", nickname, "k " + message.toString());
            
//            JSONObject json = (JSONObject) JSONSerializer.toJSON(message.toString());  
//            String type = json.getString("type");
//            String appId = json.getString("appId");
//            String text = json.getString("text");
//            System.out.println("text > " + text);
//            String from = json.getString("from");
//            String to = json.getString("to");
//            String date = json.getString("date");
            
            broadcast(message.toString());
            
            List<String> cmds = new ArrayList<String>(2);
            String dir = "";
//            String deployCmd = "mvn phresco:deploy";
//            String deployDir = "/Users/kaleeswaran/workspace/projects/Iphone-iphonehybrid";
            
            if (message.toString().contains("build")) {
//                cmd = "mvn,phresco:package";
                cmds.add("mvn");
                cmds.add("phresco:package");
                dir = "/Users/kaleeswaran/workspace/projects/Iphone-iphonehybrid";
                
            } else {
                cmds.add("mvn");
                cmds.add("phresco:deploy");
                dir = "/Users/kaleeswaran/workspace/projects/Iphone-iphonehybrid";
                
            }
            
            ProcessBuilder pb = new ProcessBuilder(cmds);
            pb.redirectErrorStream(true);
            pb.directory(new File(dir));
            try {
                Process shell = pb.start();
                // To capture output from the shell
                InputStream shellIn = shell.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(shellIn, "UTF-8"));
                while (reader.readLine () != null) {
                    broadcast("Stdout: " + reader.readLine());
    //                        System.out.println ("Stdout: " + line);
                }
            } catch (Exception e) {
                e.printStackTrace();;
            }
                
//            for (int i = 0; i < 10; i++) {
//                try {
//                    Thread.sleep(5000);
//                    broadcast(this.nickname + " ::: Client broadcat " + i);
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(ChatWebSocketServlet.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                   
//            }
            
        }

        private void broadcast(String message) {
//            for (ChatMessageInbound connection : connections) {
//                try {
//                    CharBuffer buffer = CharBuffer.wrap(message);
//                    connection.getWsOutbound().writeTextMessage(buffer);
//                } catch (IOException ignore) {
//                    // Ignore
//                }
//            }
            
            try {
                    CharBuffer buffer = CharBuffer.wrap(message);
                    this.getWsOutbound().writeTextMessage(buffer);
                } catch (IOException ignore) {
                    // Ignore
                }
        }
        
//            
//        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//            System.out.println("Do get method called ... ");
//            getServletContext().getNamedDispatcher("default").forward(request, response);
//        }
//
//        public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {       
//            System.out.println("Do post method called ... ");
//        //             String username = request.getParameter("username");
//        //        String password = request.getParameter("password");
//        }
    }
//    
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("Do get method called ... ");
//        getServletContext().getNamedDispatcher("default").forward(request, response);
//    }
//    
//    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {       
//        System.out.println("Do post method called ... ");
////             String username = request.getParameter("username");
////        String password = request.getParameter("password");
//    }
}