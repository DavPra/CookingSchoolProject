<script setup>


import { ref, onMounted } from 'vue';
import { useCourseStore } from '@/stores/CourseStore';
import { useRoute } from 'vue-router';

const courseStore = useCourseStore();
const route = useRoute();

const courses = ref([]);
const dialog = ref(false);
const editMode = ref(false);
const editedCourse = ref({
  title: '',
  teacher:'',
  startDate: '',
  description: '',
  image: '',
  maxAttendants: '',
  price: '',
});
const valid = ref(true);

onMounted(() => {
 courseStore.showCourses()
  fetchCourses();
});

const fetchCourses = async () => {
  await courseStore.showCourses();
  courses.value = courseStore.courses;
};

const openDialog = () => {
  editMode.value = false;
  editedCourse.value = { title: '',teacher:'', startDate: '', description: '', image: '', maxAttendants:'',price:'' };
  dialog.value = true;
};

const editCourse = (course) => {
  editMode.value = true;
  editedCourse.value = { ...course };
  dialog.value = true;
};

const saveCourse = () => {
  if (editMode.value) {
    const index = courses.value.findIndex((course) => course.courseId === editedCourse.value.courseId);
    courses.value[index] = { ...editedCourse.value };
  } else {
    courses.value.push({ id: courses.value.length + 1, ...editedCourse.value });
  }

  closeDialog();
};

const closeDialog = () => {
  dialog.value = false;
  valid.value = true;
  editedCourse.value = { title: '',teacher:'', startDate: '', description: '', image: '', maxAttendants:'' ,price:'' };
};

const deleteCourse = (courseId) => {
  const index = courses.value.findIndex((course) => course.id === courseId);
  courses.value.splice(index, 1);
};

</script>

<template>
  <v-container>
    <v-row>
      <v-col v-for="course in courses" :key="course.id" cols="12" md="4">
        <v-card>
          <v-img :src="course.image" height="200"></v-img>
          <v-card-title>{{ course.title }}</v-card-title>
          <v-card-subtitle>{{ course.startDate }}</v-card-subtitle>
          <v-card-text>{{ course.description }}</v-card-text>
          <v-card-actions>
            <v-btn @click="editCourse(course)">Edit</v-btn>
            <v-btn @click="deleteCourse(course.id)">Delete</v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
  <v-dialog v-model="dialog" max-width="500">
    <v-card>
      <v-card-title>{{ editMode ? 'Edit Course' : 'Add New Course' }}</v-card-title>
      <v-card-text>
        <v-form ref="form" v-model="valid" lazy-validation>
          <v-text-field v-model="editedCourse.title" label="Title" required></v-text-field>
          <v-text-field v-model="editedCourse.teacher" label="Title" required></v-text-field>
          <v-text-field v-model="editedCourse.startDate" label="Start Date" required></v-text-field>
          <v-text-field v-model="editedCourse.description" label="Description" required></v-text-field>
          <v-text-field v-model="editedCourse.image" label="Image URL" required></v-text-field>
          <v-text-field v-model.number="editedCourse.maxAttendants" label="max Attendants"></v-text-field>
          <v-text-field v-model.number="editedCourse.price" label="price"></v-text-field>

        </v-form>
      </v-card-text>
      <v-card-actions>
        <v-btn @click="saveCourse" :disabled="!valid">Save</v-btn>
        <v-btn @click="closeDialog">Cancel</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
  <v-btn @click="openDialog">Add New Course</v-btn>

</template>
<style scoped>

</style>



