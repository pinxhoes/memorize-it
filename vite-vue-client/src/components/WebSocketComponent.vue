<template>
<button @click="connectWs">Connect Websocket</button>
<button @click="initGame">Init game</button>
  <div>
    <label>WS Output</label>
    <div>{{output}}</div>
  </div>
</template>

<script setup lang="ts">

import {ref} from "vue";

const output = ref([])
let ws: WebSocket;

const connectWs = () => {
  ws = new WebSocket("ws://localhost:8080/ws/connect")
  ws.onmessage = ev => output.value.push(ev.data)
}

const initGame =  () => {
  ws.send("INIT_GAME")
}

</script>



<style scoped>

</style>