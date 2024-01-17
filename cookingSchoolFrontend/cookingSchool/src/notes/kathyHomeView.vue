<script setup>
import {ref, onMounted, onBeforeMount} from 'vue'
import {useRouter} from 'vue-router'
import {useAuthenticationStore} from '@/stores/authenticationStore'
import {useTaskStore} from '@/stores/taskStore.js'
import axios from 'axios'

const router = useRouter()
const authentication = useAuthenticationStore()
const tasksStore = useTaskStore()

async function mountFunction(){
  await authentication.getUser()
  await tasksStore.loadTasks()
}

onBeforeMount(() => {
  if (authentication.getUser() === null)
    router.push("/")
})

onMounted (() => {
  mountFunction()
})
</script>

<template>
  <v-card class="rounded-0 evelation-0 bg-deep-orange-lighten-4">
    <v-card-title class="title">Allgemeines</v-card-title>
    <v-divider></v-divider>
    <v-card-item>
      <div id="defaultListContainer" v-for="task of tasks">
        {{ tasksStore.task.title }} <span style="cursor: pointer" @click="deleteTask(task.taskId)">[x]</span>
      </div>
    </v-card-item>
  </v-card>

  <div>
    <div id="defaultListContainer"></div>
    <div id="addDefaultTaskForm">
      <input id="taskListValue" v-model="newTask" placeholder="Neue Aufgabe"/>
    </div>
  </div>

  <v-bottom-navigation>
    <v-btn @click.prevent="submitNewEntry()" prepend-icon="mdi-plus-circle">Neue Aufgabe</v-btn>
  </v-bottom-navigation>
</template>

<style scoped>
.title {
  font-size: 30px;
  font-weight: normal;
}
</style>