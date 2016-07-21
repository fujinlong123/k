
(function(){

var log = Envjs.logger('Envjs.HTML.Rhino');

Envjs.eval = function(context, source, name){
    //console.log('evaluating javascript source %s', source.substring(0,64));
	/*return  __context__.evaluateString(
        context,
        source,
        name,
        0,
        null
    );*/
	return load({name:name,script:source});
};

var __CookieUtils__=Java.type('CookieUtils');
Envjs.__httpClientContext__=org.apache.http.client.protocol.HttpClientContext.create();

Envjs.getCookies = function(url){
	return __CookieUtils__.toStr(Envjs.____httpClientContext__);
};

Envjs.setCookies = function(url){
	return __CookieUtils__.toStr(Envjs.____httpClientContext__);
};


}());