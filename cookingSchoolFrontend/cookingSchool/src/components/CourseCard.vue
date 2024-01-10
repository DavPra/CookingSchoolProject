<script setup></script>

<template>

<div class="course-list">
  <h1>Course List</h1>
  <v-row>
    <v-col v-for="course in courseStore.courses" :key="course.courseId" cols="12" sm="6" md="4" lg="3">
      <v-card>
        <v-img
            cover
            height="250"
            src="https://cdn.vuetifyjs.com/images/cards/cooking.png"
        ></v-img>

        <v-card-title>{{ course.courseTitle }}</v-card-title>
        <v-card-subtitle>{{ course.startDate }}</v-card-subtitle>
        <v-card-text>{{ course.description }}</v-card-text>
        <v-card-actions>

        </v-card-actions>
      </v-card>
    </v-col>
  </v-row>
</div>
</template>
