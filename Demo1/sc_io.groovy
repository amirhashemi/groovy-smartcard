import javax.smartcardio.*

def factory = TerminalFactory.getDefault()
def terminals = factory.terminals().list()
println "Available Terminals : " + terminals

def terminal = terminals.get(1)
def card = terminal.connect("T=0")
println "Connection Status : " + card

def atr = card.getATR()
println "**ATR : " + atr.getBytes().encodeHex()

def select = "00A4040008A000000003000000"
def cmd = new CommandAPDU(select.decodeHex())
def channel = card.getBasicChannel()
def resp = channel.transmit(cmd)
println "card << " + select
println "card >> " + resp.getBytes().encodeHex()

card.disconnect(false)
