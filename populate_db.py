import xml.etree.ElementTree as ET
import mysql.connector
from mysql.connector import Error
import os
import time
import codecs, io

conn = None
try:
    conn = mysql.connector.connect(host='localhost', 
    database='app_prog',
    user='root',
    password='root',
    auth_plugin='mysql_native_password')

    if conn.is_connected():
        print('Connected to MySQL database')

        mycursor = conn.cursor()
        sql = "drop table if exists allxml"
        mycursor.execute(sql)
        conn.commit()
        sql = ("create table if not exists allxml ("
            "nct_id varchar(255) unique not null,"
            "mesh_terms varchar(1000),"
            "enrollment_type varchar(255) default null,"
            "enrollment_number int default 0"
            ") ")
        mycursor.execute(sql)
        conn.commit()

        # do this for all files in the directory AllPublicXML
        d = 'AllPublicXML'
        for path, subdirs, files in os.walk(d):
            for name in files:
                print(str(os.path.join(path, name)))
                handle = open(os.path.join(path, name),'r')
                try:
                    as_string = handle.read()
                except UnicodeDecodeError as e:
                    print('Error: ', e)    
                    continue
                # handle.close()
                f = io.StringIO(as_string)
                tree = ET.parse(f)
                root = tree.getroot()
                mesh_string = ""
                enrollment_type = None
                num = 0

                for nct_id in root.iter('nct_id'):
                    nctid = nct_id.text
                    
                for enrollment in root.iter('enrollment'):
                    #print(enrollment.attrib) 
                    enrollment_type = "Used"
                    if 'type' in enrollment.attrib:
                        enrollment_type = enrollment.attrib['type']   
                    num = enrollment.text
                    #print(num, enrollment_type)


                for mesh_term in root.iter('mesh_term'):
                    #print(mesh_term.text)   
                    mesh_string += mesh_term.text
                    mesh_string += ', '
                # if len(mesh_string) > 2000:
                #     mesh_string = mesh_string[:2000]
                sql = ("INSERT INTO app_prog.allxml "
                        "(nct_id, enrollment_type, enrollment_number, mesh_terms)" 
                        "VALUES (%s, %s, %s, %s)")
                #print(type(nctid), type(enrollment_type), type(num), type(mesh_string))        
                val = (nctid, enrollment_type, num, mesh_string)
                #print(val)
                if enrollment_type or int(num) != 0 :
                    mycursor.execute(sql, val)
        

except Error as e:
    print('Error:', e)

finally:
    if conn is not None and conn.is_connected():
        conn.commit() 
        conn.close()



      