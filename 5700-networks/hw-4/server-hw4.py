"""
Name: Pranchal Shah
"""
import mimetypes
from http.server import SimpleHTTPRequestHandler, HTTPServer
import json
"""
The benefit of using the mimetypes module is that it not only extracts the
file extension but also attempts to guess the MIME type associated with that
extension. This can be useful when you need to work with different types of
content and want to determine how it should be processed or displayed
"""


class MyHandler(SimpleHTTPRequestHandler):
    """
    Extends the SimpleHTTPRequestHandler class to serve HTML and JSON content.
    """

    def do_GET(self):
        # Serve HTML or JSON content based on the requested resource
        if '''Insert your code here''' == "text/html":
            try:
                '''
                Insert your code here.

                '''
            except FileNotFoundError:
                '''Insert your code here'''
        elif '''Insert your code here''' == "application/json":
            try:
                '''
                Insert your code here.

                '''
            except FileNotFoundError:
                '''Insert your code here'''
        else:
            # Handle unsupported content type
            '''
            Insert your code here.

            '''


# Server setup
port = 8070
server_address = ('', port)
httpd = HTTPServer(server_address, MyHandler)

print(f"Serving on port {port}")
httpd.serve_forever()
