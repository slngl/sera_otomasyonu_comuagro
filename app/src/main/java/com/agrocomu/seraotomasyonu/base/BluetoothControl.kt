package com.agrocomu.seraotomasyonu.base

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothSocket
import android.util.Log
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream


object BluetoothControl {

    val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

    /**
     * todo: add fragment for bluetooth connection
     */

    fun connectDevice(deviceAddress: String?) {
        if (deviceAddress != null) {
            m_address = deviceAddress
        }
//        ConnectToDevice().execute()
        createConnectThread = CreateConnectThread(bluetoothAdapter, deviceAddress)
        createConnectThread!!.start()
        println(deviceAddress + "deviceAddress")
    }


    fun btWrite(input: String) {
        connectedThread?.write(input)
    }

    fun btRead(): String? {
        return receivedMessage
    }

    fun cancelConnection() {

    }

    private lateinit var receivedMessage: String
    private var m_bluetoothSocket: BluetoothSocket? = null
    private var m_isConnected: Boolean = false
    private lateinit var m_address: String

    private fun sendCommand(input: String) {
        if (m_bluetoothSocket != null) {
            try {
                m_bluetoothSocket!!.outputStream.write(input.toByteArray())
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun disconnect() {
        if (m_bluetoothSocket != null) {
            try {
                m_bluetoothSocket!!.close()
                m_bluetoothSocket = null
                m_isConnected = false
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }


    /* ============================ Thread to Create Bluetooth Connection =================================== */
    class CreateConnectThread(bluetoothAdapter: BluetoothAdapter, address: String?) : Thread() {
        override fun run() {
            val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
            bluetoothAdapter.cancelDiscovery()
            try {
                mmSocket!!.connect()
                Log.e("Status", "Device connected")
            } catch (connectException: IOException) {
                try {
                    mmSocket!!.close()
                    Log.e("Status", "Cannot connect to device")
                } catch (closeException: IOException) {
                    Log.e(TAG, "Could not close the client socket", closeException)
                }
                return
            }
            connectedThread = ConnectedThread(mmSocket)
            connectedThread!!.run()
        }

        fun cancel() {
            try {
                mmSocket!!.close()
            } catch (e: IOException) {
                Log.e(TAG, "Could not close the client socket", e)
            }
        }

        init {
            val bluetoothDevice = bluetoothAdapter.getRemoteDevice(address)
            var tmp: BluetoothSocket? = null
            val uuid =
                bluetoothDevice.uuids[0].uuid //Bu satıra dikkat et ***************************************************************
            try {
                tmp = bluetoothDevice.createInsecureRfcommSocketToServiceRecord(uuid)
            } catch (e: IOException) {
                Log.e(TAG, "Socket's create() method failed", e)
            }
            mmSocket = tmp
        }
    }

    /* =============================== Thread for Data Transfer =========================================== */
    class ConnectedThread(private val mmSocket: BluetoothSocket?) : Thread() {
        private val mmInStream: InputStream?
        private val mmOutStream: OutputStream?
        override fun run() {
            val buffer = ByteArray(1024) // buffer store for the stream
            var bytes = 0 // bytes returned from read()
            // Keep listening to the InputStream until an exception occurs
            while (true) {
                try {
                    /*
                    Read from the InputStream from Arduino until termination character is reached.
                    Then send the whole String message to GUI Handler.
                     */
                    buffer[bytes] = mmInStream!!.read().toByte()
                    var readMessage: String
                    if (buffer[bytes].toChar() == '\n') {
                        readMessage = String(buffer, 0, bytes)
                        Log.e("Arduino Message", readMessage)
                        //handler.obtainMessage(MESSAGE_READ,readMessage).sendToTarget();
                        bytes = 0
                    } else {
                        bytes++
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                    break
                }
            }
        }

        fun write(input: String) {
            val bytes = input.toByteArray() //converts entered String into bytes
            try {
                mmOutStream!!.write(bytes)
            } catch (e: IOException) {
                Log.e("Send Error", "Unable to send message", e)
            }
        }

        @Throws(IOException::class)
        fun receiveData(): String {
            val buffer = ByteArray(1024)
            val bytes: Int
            // Keep looping to listen for received messages
            while (true) {
                return try {
                    bytes = mmInStream!!.read(buffer) //read bytes from input buffer
                    val readMessage = String(buffer, 0, bytes)
                    // Send the obtained bytes to the UI Activity via handler
                    Log.i("logging", readMessage + "")
                    readMessage
                } catch (e: IOException) {
                    break
                }
            }
            return ""
        }

        /* Call this from the main activity to shutdown the connection */
        fun cancel() {
            try {
                mmSocket!!.close()
            } catch (e: IOException) {
            }
        }

        init {
            var tmpIn: InputStream? = null
            var tmpOut: OutputStream? = null
            try {
                tmpIn = mmSocket!!.inputStream
                tmpOut = mmSocket.outputStream
            } catch (e: IOException) {
            }
            mmInStream = tmpIn
            mmOutStream = tmpOut
        }
    }


    private const val REQUEST_ENABLE_BT = 1

    //    private final UUID  MY_UUID = UUID.fromString("H-C-2010-06-01");
    private const val TAG = "MY_APP_DEBUG_TAG"
    var mmSocket: BluetoothSocket? = null
    var connectedThread: ConnectedThread? = null
    var createConnectThread: CreateConnectThread? = null
    private const val CONNECTING_STATUS = 1 // used in bluetooth handler to identify message status
    private const val MESSAGE_READ = 2 // used in bluetooth handler to identify message update


}