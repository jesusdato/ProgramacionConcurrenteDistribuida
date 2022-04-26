
from awscrt import io, mqtt, auth, http
from awsiot import mqtt_connection_builder
from paho.mqtt import client as mqtt_client
import time as t
import json
import csv

# AWS VARS
ENDPOINT = "customEndpointUrl"
CLIENT_ID = "testDevice"
PATH_TO_CERTIFICATE = "certificates/a1b23cd45e-certificate.pem.crt"
PATH_TO_PRIVATE_KEY = "certificates/a1b23cd45e-private.pem.key"
PATH_TO_AMAZON_ROOT_CA_1 = "certificates/root.pem"
TOPIC = "test/testing"
event_loop_group = io.EventLoopGroup(1)
host_resolver = io.DefaultHostResolver(event_loop_group)
client_bootstrap = io.ClientBootstrap(event_loop_group, host_resolver)
mqtt_connection = mqtt_connection_builder.mtls_from_path(
        endpoint=ENDPOINT,
        cert_filepath=PATH_TO_CERTIFICATE,
        pri_key_filepath=PATH_TO_PRIVATE_KEY,
        client_bootstrap=client_bootstrap,
        ca_filepath=PATH_TO_AMAZON_ROOT_CA_1,
        client_id=CLIENT_ID,
        clean_session=False,
        keep_alive_secs=6
        )

# subscriber

# disconnect
def disconnect():
    disconnect_future = mqtt_connection.disconnect()
    disconnect_future.result()

# make conection
def connect():

    # Make the connect() call
    connect_future = mqtt_connection.connect()
    # Future.result() waits until a result is available
    connect_future.result()

#Publish
def publishPrice(prices):
    connect()
    message = {"message" : prices}
    mqtt_connection.publish(topic="Value", payload=json.dumps(message), qos=mqtt.QoS.AT_LEAST_ONCE)
    t.sleep(0.1)
    disconnect() 

#modify the price
def addComision(price):
    bid = float(price) + float(price)*0.0001
    ask = float(price) - float(price)*0.0001
    print("Ask:", ask)
    print("Bid: ",bid)
    insert=[ask,bid]
    publishPrice(insert)

#read the csv
def readmessage(msg):
    csv_reader = csv.reader(msg, delimiter=',')
    for row in csv_reader:
        print(row[2],row[3])
        addComision(row[2])
        addComision(row[3])


broker = 'broker.emqx.io'
port = 1883
topic = "python/mqtt"
# generate client ID with pub prefix randomly
client_id = f'python-mqtt-{random.randint(0, 100)}'
username = 'emqx'
password = 'public'


def connect_mqtt() -> mqtt_client:
    def on_connect(client, userdata, flags, rc):
        if rc == 0:
            print("Connected to MQTT Broker!")
        else:
            print("Failed to connect, return code %d\n", rc)

    client = mqtt_client.Client(client_id)
    client.username_pw_set(username, password)
    client.on_connect = on_connect
    client.connect(broker, port)
    return client


def subscribe(client: mqtt_client):
    def on_message(client, userdata, msg):
        print(f"Received `{msg.payload.decode()}` from `{msg.topic}` topic")

    client.subscribe(topic)
    client.on_message = on_message


def run():
    client = connect_mqtt()
    subscribe(client)
    client.loop_forever()
