<template>
  <button @click="connectWs">Connect Websocket</button>
  <button id="start-button" @click="initGame">Start</button>
  <div id="game-board">
    <div v-for="(item, index) in nums" class="card"
         :id="index.toString()"
          @click="flip(index)"
    >{{flipped.includes(index) ? item : '?'}}</div>
  </div>
  <div>
    <label>WS Output</label>
    <div>{{ output }}</div>
  </div>
</template>

<script setup lang="ts">

import {ref} from "vue";

const output = ref([])
const flipped = ref<number[]>([])
const nums = ref<number[]>([])
let ws: WebSocket;

const flip = (idx: number) => {
  if (flipped.value.length < 2) {
    flipped.value.push(idx);
  }
}

const connectWs = () => {
  ws = new WebSocket("ws://localhost:8080/ws/connect")
  ws.onmessage = ev => {
    if (ev.data == "Client connected") {
      output.value.push(ev.data)
    } else {
      const parsed = JSON.parse(ev.data);
      console.log(parsed)
      nums.value = parsed
    }
  }
}

const initGame = () => {
  ws.send("INIT_GAME")
}

</script>


<style scoped>
body {
  font-family: Arial, sans-serif;
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  text-align: center;
}

#game-board {
  display: grid;
  grid-template-columns: repeat(5, 1fr);
  grid-template-rows: repeat(4, 1fr);
  gap: 10px;
  margin: 20px auto;
}

button {
  padding: 30px 50px;
  background-color: #3498db;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  margin: 20px 0;
}

.card {
  padding: 80px 80px;
  background-color: #3498db;
  color: white;
  display: flex;
  font-size: 24px;
  cursor: pointer;
  border-radius: 8px;
}

.card:hover {
  background-color: #5da5d5;
}
</style>