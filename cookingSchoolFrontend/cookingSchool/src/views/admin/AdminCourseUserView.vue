<script setup>
import { useRouter } from 'vue-router';
import {ref, defineProps, onMounted} from 'vue';
import {useUserStore} from "@/stores/UserStore";
import {useCourseStore} from "@/stores/CourseStore";


const router = useRouter();
const courseId = defineProps(['courseId']);
console.log(courseId)
const dialog = ref(false);
const userStore = useUserStore()
const userIdInput = ref('');
const courseIdValue = courseId.courseId;
console.log(courseIdValue);
const courseStore = useCourseStore()


const courseDetails = ref({
  courseTitle: '',
  maxAttendants: '',
  users:[
    {
      userId: '',
      username: ''
    }
  ]

});

onMounted(async () => {
  try {
    courseDetails.value = await courseStore.getCourseById(courseIdValue);
  } catch (error) {
    console.error(error);
  }
});

onMounted(() => {
  userStore.showUsers();
});
const openDialog = () => {
  dialog.value = true;
};

const closeDialog = () => {
  dialog.value = false;
};

const closeAdminCourseUserView = () => {
  router.go(-1);
};

const addUserToCourse = () => {

  const userId = userIdInput.value.trim();

  if (userId) {
    try {
      userStore.addUserToCourse(userId, courseIdValue);
      console.log(courseIdValue)
      console.log(`User ${userId} added to course ${courseIdValue}`);
   closeDialog()
    } catch (error) {
      console.error(error);
    }
  } else {
    console.warn('No user selected.');
  }
};


</script>
<template>

  <v-card elevation="3" width="500" class="mx-auto">
    <v-card-title>{{courseDetails.courseTitle}}</v-card-title>
    <v-card-subtitle>Freie Plätze: {{courseDetails.maxAttendants}}</v-card-subtitle>
  <v-card-text>
    Teilnehmer:

  </v-card-text>
    <v-list :items="courseDetails.users" item-title="username" item-value="userId" lines="one">
    </v-list>
    <v-card-actions>
      <v-btn @click="dialog= true">add User </v-btn>
    </v-card-actions>
  </v-card>

  <v-dialog width="500" v-model="dialog">
    <v-card>

        <v-col cols="12">
          <v-text-field v-model="userIdInput" label="User ID" outlined></v-text-field>
          <v-btn @click="addUserToCourse">Add User</v-btn>
          <v-btn @click="closeDialog">Cancel</v-btn>
        </v-col>

    </v-card>
  </v-dialog>


</template>



<style scoped>

</style>