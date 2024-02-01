"""
Name Pranchal Shah
"""

import http.client
import sys


def get_resource(resource):
    '''
    Insert your code here.
    '''
    conn = http.client.HTTPConnection('localhost', 8070)
    conn.request("GET", resource)
    response = conn.getresponse()
    status_code = response.status
    content_type = response.getheader('Content-Type')
    content = response.read()
    print(f"Response Status Code: {status_code}")
    print(f"Content Type: {content_type}")
    print("Response Content:")
    print(content.decode())

    conn.close()


get_resource(sys.argv[1])
