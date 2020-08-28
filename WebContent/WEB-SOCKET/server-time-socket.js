/**
 * Прикључиница за приказ серверског времена. 
 */
"UTF-8"

class ServerTimeSocket{
    constructor(protocol, hostname, port, endpoint) {
        this.webSocket = null;
        this.protocol = protocol;
        this.hostname = hostname;
        this.port     = port;
        this.endpoint = endpoint;
    }
	

    getServerUrl() {
        return this.protocol + "://" + this.hostname + ":" + this.port + this.endpoint;
    }
    
    connect() {
        try {
            this.webSocket = new WebSocket(this.getServerUrl());
                 
            this.webSocket.onmessage = function(event) {
                var msg = event.data;
				document.getElementById('server_time').innerHTML=msg; 
            }
        } catch (exception) {}
    }
    
    getStatus() {
        return this.webSocket.readyState;
    }
 
    disconnect() {
        if (this.webSocket.readyState == WebSocket.OPEN) 
            this.webSocket.close();  
    }
}