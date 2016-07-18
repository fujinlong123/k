/*
 * Envjs rhino-env.1.3.pre03
 * Pure JavaScript Browser Environment
 * By John Resig <http://ejohn.org/> and the Envjs Team
 * Copyright 2008-2010 John Resig, under the MIT License
 */

load("nashorn:mozilla_compat.js");
var Envjs = Envjs || 
	require('envjs/platform/core').Envjs;
	require('local_settings');


//var __context__ = Packages.org.mozilla.javascript.Context.getCurrentContext();
var __httpClientContext__=org.apache.http.client.protocol.HttpClientContext.create();

var __httpUtils__=Java.type('HttpUtils');
Envjs.platform       = "Rhino";
Envjs.revision       = "1.7.0.rc2";
Envjs.argv = [];
if(__argv__ && __argv__.length){
	for(var i = 0; i < __argv__.length; i++){
		Envjs.argv[i] = __argv__[i];
	}
}

Envjs.exit = function(){
	java.lang.System.exit(0);
};

/*
 * Envjs rhino-env.1.3.pre03 
 * Pure JavaScript Browser Environment
 * By John Resig <http://ejohn.org/> and the Envjs Team
 * Copyright 2008-2010 John Resig, under the MIT License
 */

//CLOSURE_START
(function(){





/**
 * @author john resig
 */
// Helper method for extending one object with another.
function __extend__(a,b) {
    for ( var i in b ) {
        if(b.hasOwnProperty(i)){
            var g = b.__lookupGetter__(i), s = b.__lookupSetter__(i);
            if ( g || s ) {
                if ( g ) { a.__defineGetter__(i, g); }
                if ( s ) { a.__defineSetter__(i, s); }
            } else {
                a[i] = b[i];
            }
        }
    } 
    return a;
}

/**
 * Writes message to system out.
 *
 * @param {Object} message
 */
(function(){
	
Envjs.log = print;

Envjs.lineSource = function(e){
    return e&&e.rhinoException?e.rhinoException.lineSource():"(line ?)";
};

var $in, log; 
Envjs.readConsole = function(){
	log = log||Envjs.logger('Envjs.Rhino');
	$in = $in||new java.io.BufferedReader(
		new java.io.InputStreamReader(java.lang.System['in'])
	);
	return  $in.readLine()+'';
};
Envjs.prompt = function(){
  	java.lang.System.out.print(Envjs.CURSOR+' '); 
	java.lang.System.out.flush();
};

}());


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

}());Envjs.renderSVG = function(svgstring, url){
    //console.log("svg template url %s", templateSVG);
    // Create a JPEG transcoder
    var t = new Packages.org.apache.batik.transcoder.image.JPEGTranscoder();

    // Set the transcoding hints.
    t.addTranscodingHint(
        Packages.org.apache.batik.transcoder.image.JPEGTranscoder.KEY_QUALITY,
        new java.lang.Float(1.0));
    // Create the transcoder input.
    var input = new Packages.org.apache.batik.transcoder.TranscoderInput(
        new java.io.StringReader(svgstring));

    // Create the transcoder output.
    var ostream = new java.io.ByteArrayOutputStream();
    var output = new Packages.org.apache.batik.transcoder.TranscoderOutput(ostream);

    // Save the image.
    t.transcode(input, output);

    // Flush and close the stream.
    ostream.flush();
    ostream.close();
    
	var out = new java.io.FileOutputStream(new java.io.File(new java.net.URI(url.toString())));
	try{
    	out.write( ostream.toByteArray() );
	}catch(e){
		
	}finally{
    	out.flush();
    	out.close();
    }
};
(function(){

var log = Envjs.logger('Envjs.Timer.Rhino');
/**
 * Rhino provides a very succinct 'sync'
 * @param {Function} fn
 */
try{
    Envjs.sync = sync;
    Envjs.spawn = spawn;
	//print('sync and spawn are available');
} catch(e){	
	//print('sync and spawn are not available : ' + e);
    //sync unavailable on AppEngine
    Envjs.sync = function(fn){
        console.log('Threadless platform, sync is safe');
        return fn;
    };

    Envjs.spawn = function(fn){
        console.log('Threadless platform, spawn shares main thread.');
        return fn();
    };
};


/**
 * sleep thread for specified duration
 * @param {Object} milliseconds
 */
Envjs.sleep = function(milliseconds){
    try{
        return java.lang.Thread.sleep(milliseconds);
    }catch(e){
        console.log('Threadless platform, cannot sleep.');
    }
};

/**
 * provides callback hook for when the system exits
 */
Envjs.onExit = function(callback){
    var rhino = Packages.org.mozilla.javascript,
        contextFactory =  __context__.getFactory(),
        listener = new rhino.ContextFactory.Listener({
            contextReleased: function(context){
                if(context === __context__)
                    console.log('context released', context);
                contextFactory.removeListener(this);
                if(callback)
                    callback();
            }
        });
    contextFactory.addListener(listener);
};

}());
(function(){

var log = Envjs.logger('Envjs.XMLHttpRequest.Rhino');

/**
 * Get 'Current Working Directory'
 */
Envjs.getcwd = function() {
    return java.lang.System.getProperty('user.dir');
}


/**
 * Used to write to a local file
 * @param {Object} text
 * @param {Object} url
 */
Envjs.writeToFile = function(text, url){
    //Envjs.debug("writing text to url : " + url);
    var out = new java.io.FileWriter(
        new java.io.File(
            new java.net.URI(url.toString())));
    out.write( text, 0, text.length );
    out.flush();
    out.close();
};

/**
 * Used to write to a local file
 * @param {Object} text
 * @param {Object} suffix
 */
Envjs.writeToTempFile = function(text, suffix){
    //console.log("writing text to temp url : %s");
    // Create temp file.
    var temp = java.io.File.createTempFile("envjs-tmp", suffix);

    // Delete temp file when program exits.
    temp.deleteOnExit();

    // Write to temp file
    var out = new java.io.FileWriter(temp);
    out.write(text, 0, text.length);
    out.close();
    return temp.getAbsolutePath().toString()+'';
};


/**
 * Used to read the contents of a local file
 * @param {Object} url
 */
Envjs.readFromFile = function( url ){
	if(typeof url == 'string')
    	url = Envjs.uri(url);
    //console.log("reading from url : %s", url);
    var fileReader = new java.io.FileReader(
        new java.io.File( 
            new java.net.URI( url )));
            
    var stringwriter = new java.io.StringWriter(),
        buffer = java.lang.reflect.Array.newInstance(java.lang.Character.TYPE, 1024),
        length;

    while ((length = fileReader.read(buffer, 0, 1024)) != -1) {
        stringwriter.write(buffer, 0, length);
    }

    stringwriter.close();
    return stringwriter.toString()+"";
};
    

/**
 * Used to delete a local file
 * @param {Object} url
 */
Envjs.deleteFile = function(url){
    var file = new java.io.File( new java.net.URI( url ) );
    file["delete"]();
};

/**
 * establishes connection and calls responsehandler
 * @param {Object} xhr
 * @param {Object} responseHandler
 * @param {Object} data
 */
Envjs.connection = function(xhr, responseHandler, data){
    var url = xhr.url,
        connection,
        header,
        outstream,
        buffer,
        length,
        binary = false,
        name, value,
        contentEncoding,
        instream,
        responseXML,
        i;
    
        
    if ( /^file\:/.test(url) ) {
        Envjs.localXHR(url, xhr, connection, data);
    } else {
       // connection = url.openConnection();
        //handle redirects manually since cookie support sucks out of the box
        //connection.setFollowRedirects(false);
        //connection.setRequestMethod( xhr.method );

        // Add headers to Java connection
       // for (header in xhr.headers){
         //   connection.addRequestProperty(header+'', xhr.headers[header]+'');
       // }
        //connection.addRequestProperty("Accept-Encoding", 'gzip');

        //write data to output stream if required
		//TODO: if they have set the request header for a chunked
		//request body, implement a chunked output stream
        if(xhr.method!='GET'){
        	throw new Exception("只支持get请求");
        }
        var objectResponse;
        if(data){
        	
            if(data instanceof Document){
                if ( xhr.method == "PUT" || xhr.method == "POST" ) {
                    connection.setDoOutput(true);
                    outstream = connection.getOutputStream(),
                    xml = (new XMLSerializer()).serializeToString(data);
                    buffer = new java.lang.String(xml).getBytes('UTF-8');
                    outstream.write(buffer, 0, buffer.length);
                    outstream.close();
                }
            }else if(data.length&&data.length>0){
                if ( xhr.method == "PUT" || xhr.method == "POST" ) {
                    connection.setDoOutput(true);
                    outstream = connection.getOutputStream();
                    buffer = new java.lang.String(data).getBytes('UTF-8');
                    outstream.write(buffer, 0, buffer.length);
                    outstream.close();
                }
            }
            connection.connect();
        }else{
        	
        	objectResponse=__httpUtils__.get(url,__httpClientContext__);
            //connection.connect();
        }
    }

    //if(connection){
     /*   try{
            length = connection.getHeaderFields().size();
            // Stick the response headers into responseHeaders
            for (i = 0; i < length; i++) {
                name = connection.getHeaderFieldKey(i);
                value = connection.getHeaderField(i);
                if (name)
                    xhr.responseHeaders[name+''] = value+'';
            }
        }catch(e){
            console.log('failed to load response headers \n%s',e);
        }*/

    	
    
    
        xhr.readyState = 4;
      //  xhr.status = parseInt(connection.responseCode,10) || undefined;
       // xhr.statusText = connection.responseMessage || "";
        
        
        xhr.status =objectResponse.statusCode || undefined;
        xhr.statusText = "";

        contentEncoding = objectResponse.charset || "utf-8";
        instream = null;
        responseXML = null;
        
       /* try{
            //console.log('contentEncoding %s', contentEncoding);
            if( contentEncoding.equalsIgnoreCase("gzip") ||
                contentEncoding.equalsIgnoreCase("decompress")){
                //zipped content
                binary = true;
                outstream = new java.io.ByteArrayOutputStream();
                buffer = java.lang.reflect.Array.newInstance(java.lang.Byte.TYPE, 1024);
                instream = new java.util.zip.GZIPInputStream(connection.getInputStream())
            }else{
                //this is a text file
                outstream = new java.io.StringWriter();
                buffer = java.lang.reflect.Array.newInstance(java.lang.Character.TYPE, 1024);
                instream = new java.io.InputStreamReader(connection.getInputStream());
            }
        }catch(e){
            if (connection.getResponseCode() == 404){
                console.log('failed to open connection stream \n %s %s',
                            e.toString(), e);
            }else{
                console.log('failed to open connection stream \n %s %s',
                            e.toString(), e);
            }
            instream = connection.getErrorStream();
        }

        while ((length = instream.read(buffer, 0, 1024)) != -1) {
            outstream.write(buffer, 0, length);
        }

        outstream.close();
        instream.close();*/
       // print("objectResponse.isBinary:"+objectResponse.isBinary);
        if(objectResponse.isBinary()){
            xhr.responseBinary = objectResponse.binaryResponseBody;
        }else{
            xhr.responseText = objectResponse.stringResponseBody;
        }

    //}
    if(responseHandler){
        //console.log('calling ajax response handler');
        if(!xhr.async){
			responseHandler();
		}else{
		    //console.log('synchronizing ajax response handler via setTimeout');
			setTimeout(responseHandler, 1);
		}
    }
};

}());


(function(){

var log = Envjs.logger('Envjs.Window.Rhino');

//Since we're running in rhino I guess we can safely assume
//java is 'enabled'.  I'm sure this requires more thought
//than I've given it here
Envjs.javaEnabled = true;

Envjs.homedir        = java.lang.System.getProperty("user.home");
Envjs.tmpdir         = java.lang.System.getProperty("java.io.tmpdir");
Envjs.os_name        = java.lang.System.getProperty("os.name");
Envjs.os_arch        = java.lang.System.getProperty("os.arch");
Envjs.os_version     = java.lang.System.getProperty("os.version");
Envjs.lang           = java.lang.System.getProperty("user.lang");


Envjs.gc = function(){ gc(); };

/**
 * Makes an object window-like by proxying object accessors
 * @param {Object} scope
 * @param {Object} parent
 */
/*Envjs.proxy = function(scope, parent) {
    try{
        if(scope+'' == '[object global]'){
            return scope;
        }else{
            return  new javax.script.SimpleScriptContext();
        }
    }catch(e){
        console.log('failed to init standard objects %s %s \n%s', scope, parent, e);
    }

};*/

}());/*

 JS Beautifier
---------------
  $Date: 2008-06-10 14:49:11 +0300 (Tue, 10 Jun 2008) $
  $Revision: 60 $


  Written by Einars "elfz" Lielmanis, <elfz@laacz.lv> 
      http://elfz.laacz.lv/beautify/

  Originally converted to javascript by Vital, <vital76@gmail.com> 
      http://my.opera.com/Vital/blog/2007/11/21/javascript-beautify-on-javascript-translated


  You are free to use this in any way you want, in case you find this useful or working for you.

  Usage:
    js_beautify(js_source_text);

 */
(function() {
	var isbeautify = true;

	Envjs.js_beautify = function(js_source_text, indent_size,
			indent_character, indent_level) {
		if (!isbeautify) {
			return js_source_text;
		}
		var input, output, token_text, last_type, last_text, last_word, current_mode, modes, indent_string;
		var whitespace, wordchar, punct, parser_pos, line_starters, in_case;
		var prefix, token_type, do_block_just_closed, var_line, var_line_tainted;

		function trim_output() {
			while (output.length
					&& (output[output.length - 1] === ' ' || output[output.length - 1] === indent_string)) {
				output.pop();
			}
		}

		function print_newline(ignore_repeated) {
			ignore_repeated = typeof ignore_repeated === 'undefined' ? true
					: ignore_repeated;

			trim_output();

			if (!output.length) {
				return; // no newline on start of file
			}

			if (output[output.length - 1] !== "\n" || !ignore_repeated) {
				output.push("\n");
			}
			for (var i = 0; i < indent_level; i++) {
				output.push(indent_string);
			}
		}

		function print_space() {
			var last_output = output.length ? output[output.length - 1] : ' ';
			if (last_output !== ' ' && last_output !== '\n'
					&& last_output !== indent_string) { // prevent occassional
				// duplicate space
				output.push(' ');
			}
		}

		function print_token() {
			output.push(token_text);
		}

		function indent() {
			indent_level++;
		}

		function unindent() {
			if (indent_level) {
				indent_level--;
			}
		}

		function remove_indent() {
			if (output.length && output[output.length - 1] === indent_string) {
				output.pop();
			}
		}

		function set_mode(mode) {
			modes.push(current_mode);
			current_mode = mode;
		}

		function restore_mode() {
			do_block_just_closed = current_mode === 'DO_BLOCK';
			current_mode = modes.pop();
		}

		function in_array(what, arr) {
			for (var i = 0; i < arr.length; i++) {
				if (arr[i] === what) {
					return true;
				}
			}
			return false;
		}

		function get_next_token() {
			var n_newlines = 0;
			var c = '';

			do {
				if (parser_pos >= input.length) {
					return [ '', 'TK_EOF' ];
				}
				c = input.charAt(parser_pos);

				parser_pos += 1;
				if (c === "\n") {
					n_newlines += 1;
				}
			} while (in_array(c, whitespace));

			if (n_newlines > 1) {
				for (var i = 0; i < 2; i++) {
					print_newline(i === 0);
				}
			}
			var wanted_newline = (n_newlines === 1);

			if (in_array(c, wordchar)) {
				if (parser_pos < input.length) {
					while (in_array(input.charAt(parser_pos), wordchar)) {
						c += input.charAt(parser_pos);
						parser_pos += 1;
						if (parser_pos === input.length) {
							break;
						}
					}
				}

				// small and surprisingly unugly hack for 1E-10 representation
				if (parser_pos !== input.length && c.match(/^[0-9]+[Ee]$/)
						&& input.charAt(parser_pos) === '-') {
					parser_pos += 1;

					var t = get_next_token(parser_pos);
					c += '-' + t[0];
					return [ c, 'TK_WORD' ];
				}

				if (c === 'in') { // hack for 'in' operator
					return [ c, 'TK_OPERATOR' ];
				}
				return [ c, 'TK_WORD' ];
			}

			if (c === '(' || c === '[') {
				return [ c, 'TK_START_EXPR' ];
			}

			if (c === ')' || c === ']') {
				return [ c, 'TK_END_EXPR' ];
			}

			if (c === '{') {
				return [ c, 'TK_START_BLOCK' ];
			}

			if (c === '}') {
				return [ c, 'TK_END_BLOCK' ];
			}

			if (c === ';') {
				return [ c, 'TK_END_COMMAND' ];
			}

			if (c === '/') {
				var comment = '';
				// peek for comment /* ... */
				if (input.charAt(parser_pos) === '*') {
					parser_pos += 1;
					if (parser_pos < input.length) {
						while (!(input.charAt(parser_pos) === '*'
								&& input.charAt(parser_pos + 1) && input
								.charAt(parser_pos + 1) === '/')
								&& parser_pos < input.length) {
							comment += input.charAt(parser_pos);
							parser_pos += 1;
							if (parser_pos >= input.length) {
								break;
							}
						}
					}
					parser_pos += 2;
					return [ '/*' + comment + '*/', 'TK_BLOCK_COMMENT' ];
				}
				// peek for comment // ...
				if (input.charAt(parser_pos) === '/') {
					comment = c;
					while (input.charAt(parser_pos) !== "\x0d"
							&& input.charAt(parser_pos) !== "\x0a") {
						comment += input.charAt(parser_pos);
						parser_pos += 1;
						if (parser_pos >= input.length) {
							break;
						}
					}
					parser_pos += 1;
					if (wanted_newline) {
						print_newline();
					}
					return [ comment, 'TK_COMMENT' ];
				}

			}

			if (c === "'"
					|| // string
					c === '"'
					|| // string
					(c === '/' && ((last_type === 'TK_WORD' && last_text === 'return') || (last_type === 'TK_START_EXPR'
							|| last_type === 'TK_END_BLOCK'
							|| last_type === 'TK_OPERATOR'
							|| last_type === 'TK_EOF' || last_type === 'TK_END_COMMAND')))) { // regexp
				var sep = c;
				var esc = false;
				c = '';

				if (parser_pos < input.length) {

					while (esc || input.charAt(parser_pos) !== sep) {
						c += input.charAt(parser_pos);
						if (!esc) {
							esc = input.charAt(parser_pos) === '\\';
						} else {
							esc = false;
						}
						parser_pos += 1;
						if (parser_pos >= input.length) {
							break;
						}
					}

				}

				parser_pos += 1;
				if (last_type === 'TK_END_COMMAND') {
					print_newline();
				}
				return [ sep + c + sep, 'TK_STRING' ];
			}

			if (in_array(c, punct)) {
				while (parser_pos < input.length
						&& in_array(c + input.charAt(parser_pos), punct)) {
					c += input.charAt(parser_pos);
					parser_pos += 1;
					if (parser_pos >= input.length) {
						break;
					}
				}
				return [ c, 'TK_OPERATOR' ];
			}

			return [ c, 'TK_UNKNOWN' ];
		}

		// ----------------------------------

		indent_character = indent_character || ' ';
		indent_size = indent_size || 4;

		indent_string = '';
		while (indent_size--) {
			indent_string += indent_character;
		}

		input = js_source_text;

		last_word = ''; // last 'TK_WORD' passed
		last_type = 'TK_START_EXPR'; // last token type
		last_text = ''; // last token text
		output = [];

		do_block_just_closed = false;
		var_line = false;
		var_line_tainted = false;

		whitespace = "\n\r\t ".split('');
		wordchar = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_$'
				.split('');
		punct = '+ - * / % & ++ -- = += -= *= /= %= == === != !== > < >= <= >> << >>> >>>= >>= <<= && &= | || ! !! , : ? ^ ^= |='
				.split(' ');

		// words which should always start on new line.
		line_starters = 'continue,try,throw,return,var,if,switch,case,default,for,while,break,function'
				.split(',');

		// states showing if we are currently in expression (i.e. "if" case) -
		// 'EXPRESSION', or in usual block (like, procedure), 'BLOCK'.
		// some formatting depends on that.
		current_mode = 'BLOCK';
		modes = [ current_mode ];

		indent_level = indent_level || 0;
		parser_pos = 0; // parser position
		in_case = false; // flag for parser that case/default has been
		// processed, and next colon needs special attention
		while (true) {
			var t = get_next_token(parser_pos);
			token_text = t[0];
			token_type = t[1];
			if (token_type === 'TK_EOF') {
				break;
			}

			switch (token_type) {

			case 'TK_START_EXPR':
				var_line = false;
				set_mode('EXPRESSION');
				if (last_type === 'TK_END_EXPR'
						|| last_type === 'TK_START_EXPR') {
					// do nothing on (( and )( and ][ and ]( ..
				} else if (last_type !== 'TK_WORD'
						&& last_type !== 'TK_OPERATOR') {
					print_space();
				} else if (in_array(last_word, line_starters)
						&& last_word !== 'function') {
					print_space();
				}
				print_token();
				break;

			case 'TK_END_EXPR':
				print_token();
				restore_mode();
				break;

			case 'TK_START_BLOCK':

				if (last_word === 'do') {
					set_mode('DO_BLOCK');
				} else {
					set_mode('BLOCK');
				}
				if (last_type !== 'TK_OPERATOR'
						&& last_type !== 'TK_START_EXPR') {
					if (last_type === 'TK_START_BLOCK') {
						print_newline();
					} else {
						print_space();
					}
				}
				print_token();
				indent();
				break;

			case 'TK_END_BLOCK':
				if (last_type === 'TK_START_BLOCK') {
					// nothing
					trim_output();
					unindent();
				} else {
					unindent();
					print_newline();
				}
				print_token();
				restore_mode();
				break;

			case 'TK_WORD':

				if (do_block_just_closed) {
					print_space();
					print_token();
					print_space();
					break;
				}

				if (token_text === 'case' || token_text === 'default') {
					if (last_text === ':') {
						// switch cases following one another
						remove_indent();
					} else {
						// case statement starts in the same line where switch
						unindent();
						print_newline();
						indent();
					}
					print_token();
					in_case = true;
					break;
				}

				prefix = 'NONE';
				if (last_type === 'TK_END_BLOCK') {
					if (!in_array(token_text.toLowerCase(), [ 'else', 'catch',
							'finally' ])) {
						prefix = 'NEWLINE';
					} else {
						prefix = 'SPACE';
						print_space();
					}
				} else if (last_type === 'TK_END_COMMAND'
						&& (current_mode === 'BLOCK' || current_mode === 'DO_BLOCK')) {
					prefix = 'NEWLINE';
				} else if (last_type === 'TK_END_COMMAND'
						&& current_mode === 'EXPRESSION') {
					prefix = 'SPACE';
				} else if (last_type === 'TK_WORD') {
					prefix = 'SPACE';
				} else if (last_type === 'TK_START_BLOCK') {
					prefix = 'NEWLINE';
				} else if (last_type === 'TK_END_EXPR') {
					print_space();
					prefix = 'NEWLINE';
				}

				if (last_type !== 'TK_END_BLOCK'
						&& in_array(token_text.toLowerCase(), [ 'else',
								'catch', 'finally' ])) {
					print_newline();
				} else if (in_array(token_text, line_starters)
						|| prefix === 'NEWLINE') {
					if (last_text === 'else') {
						// no need to force newline on else break
						print_space();
					} else if ((last_type === 'TK_START_EXPR' || last_text === '=')
							&& token_text === 'function') {
						// no need to force newline on 'function': (function
						// DONOTHING
					} else if (last_type === 'TK_WORD'
							&& (last_text === 'return' || last_text === 'throw')) {
						// no newline between 'return nnn'
						print_space();
					} else if (last_type !== 'TK_END_EXPR') {
						if ((last_type !== 'TK_START_EXPR' || token_text !== 'var')
								&& last_text !== ':') {
							// no need to force newline on 'var': for (var x =
							// 0...)
							if (token_text === 'if' && last_type === 'TK_WORD'
									&& last_word === 'else') {
								// no newline for } else if {
								print_space();
							} else {
								print_newline();
							}
						}
					} else {
						if (in_array(token_text, line_starters)
								&& last_text !== ')') {
							print_newline();
						}
					}
				} else if (prefix === 'SPACE') {
					print_space();
				}
				print_token();
				last_word = token_text;

				if (token_text === 'var') {
					var_line = true;
					var_line_tainted = false;
				}

				break;

			case 'TK_END_COMMAND':

				print_token();
				var_line = false;
				break;

			case 'TK_STRING':

				if (last_type === 'TK_START_BLOCK'
						|| last_type === 'TK_END_BLOCK') {
					print_newline();
				} else if (last_type === 'TK_WORD') {
					print_space();
				}
				print_token();
				break;

			case 'TK_OPERATOR':

				var start_delim = true;
				var end_delim = true;
				if (var_line && token_text !== ',') {
					var_line_tainted = true;
					if (token_text === ':') {
						var_line = false;
					}
				}

				if (token_text === ':' && in_case) {
					print_token(); // colon really asks for separate treatment
					print_newline();
					break;
				}

				in_case = false;

				if (token_text === ',') {
					if (var_line) {
						if (var_line_tainted) {
							print_token();
							print_newline();
							var_line_tainted = false;
						} else {
							print_token();
							print_space();
						}
					} else if (last_type === 'TK_END_BLOCK') {
						print_token();
						print_newline();
					} else {
						if (current_mode === 'BLOCK') {
							print_token();
							print_newline();
						} else {
							// EXPR od DO_BLOCK
							print_token();
							print_space();
						}
					}
					break;
				} else if (token_text === '--' || token_text === '++') { // unary
					// operators
					// special
					// case
					if (last_text === ';') {
						// space for (;; ++i)
						start_delim = true;
						end_delim = false;
					} else {
						start_delim = false;
						end_delim = false;
					}
				} else if (token_text === '!' && last_type === 'TK_START_EXPR') {
					// special case handling: if (!a)
					start_delim = false;
					end_delim = false;
				} else if (last_type === 'TK_OPERATOR') {
					start_delim = false;
					end_delim = false;
				} else if (last_type === 'TK_END_EXPR') {
					start_delim = true;
					end_delim = true;
				} else if (token_text === '.') {
					// decimal digits or object.property
					start_delim = false;
					end_delim = false;

				} else if (token_text === ':') {
					// zz: xx
					// can't differentiate ternary op, so for now it's a ? b: c;
					// without space before colon
					if (last_text.match(/^\d+$/)) {
						// a little help for ternary a ? 1 : 0;
						start_delim = true;
					} else {
						start_delim = false;
					}
				}
				if (start_delim) {
					print_space();
				}

				print_token();

				if (end_delim) {
					print_space();
				}
				break;

			case 'TK_BLOCK_COMMENT':

				print_newline();
				print_token();
				print_newline();
				break;

			case 'TK_COMMENT':

				// print_newline();
				print_space();
				print_token();
				print_newline();
				break;

			case 'TK_UNKNOWN':
				print_token();
				break;
			}

			last_type = token_type;
			last_text = token_text;
		}

		return output.join('');

	}
})();
/**
 * @author john resig & the envjs team
 * @uri http://www.envjs.com/
 * @copyright 2008-2010
 * @license MIT
 */
//CLOSURE_END
}());
