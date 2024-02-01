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

        if mimetypes.guess_type(self.path)[0] == "text/html":
            try:
                self.send_response(200)
                self.send_header("Content-type", "text/html")
                self.end_headers()
                self.wfile.write(open(self.path, "r").read().encode())

            except FileNotFoundError:
                """
                Response Status Code: 200
Content Type: text/html
Response Content:
HTTP/1.0 404 File Not Found: FileDoesNotExist.html
Server: SimpleHTTP/0.6 Python/3.12.1
Date: Sun, 21 Jan 2024 06:14:48 GMT
Connection: close
Content-Type: text/html;charset=utf-8
Content-Length: 35
                """
                self.send_response(200)
                self.send_header("Content-type", "text/plain")
                self.end_headers()
                self.wfile.write(f"404 File Not Found: {self.path} ".encode())

        elif mimetypes.guess_type(self.path)[0] == "application/json":
            try:
                self.send_response(200)
                self.send_header("Content-type", "application/json")
                self.end_headers()
                # opens the file, reads the content, and encodes it to bytes
                # eventually writes to the client
                self.wfile.write(open(self.path, "r").read().encode())
            except FileNotFoundError:
                self.send_response(404)
                self.send_header("Content-type", "text/plain")
                self.end_headers()
                self.wfile.write("404 Not Found".encode())
        else:
            # Handle unsupported content type
            self.send_response(404)
            self.send_header("Content-type", "text/plain")
            self.end_headers()
            self.wfile.write(
                "404 Not Found - Unsupported Content Type".encode())


# Server setup
port = 8070
server_address = ('', port)
httpd = HTTPServer(server_address, MyHandler)

print(f"Serving on port {port}")
httpd.serve_forever()
