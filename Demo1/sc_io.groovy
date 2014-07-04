//Importing java smartcardio api.
import javax.smartcardio.*

//Accessing the terminal manager and listing the available terminals on PC.
def factory = TerminalFactory.getDefault()
def terminals = factory.terminals().list()
println "Available Terminals : " + terminals

//Connecting to one of the terminals in the list by its index.
def terminal = terminals.get(1)
def card = terminal.connect("T=0")
println "Connection Status : " + card

//Requesting the card Answer to Reset
def atr = card.getATR()
println "**ATR : " + atr.getBytes().encodeHex()

//Sending a select default Card Manager to a javacard insereted.
def select = "00A4040008A000000003000000"
def cmd = new CommandAPDU(select.decodeHex())
def channel = card.getBasicChannel()
def resp = channel.transmit(cmd)
println "card << " + select
println "card >> " + resp.getBytes().encodeHex()

//Disconnecting the communication.
card.disconnect(false)
