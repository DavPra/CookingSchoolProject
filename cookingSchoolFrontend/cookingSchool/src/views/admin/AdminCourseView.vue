<script setup>
import {ref, onMounted, onUpdated, watchEffect} from 'vue';
import {useRoute, useRouter} from 'vue-router';
import {useCourseStore} from "@/stores/CourseStore";
import AdminCourseAddUserView from "@/views/admin/AdminCourseUserView.vue";
import {globalColors as __colors} from "@/assets/colors";

const errorMsg = ref('');
const courseStore = useCourseStore();
const route = useRoute();
const show = ref(false)
const courses = ref([]);
const dialog = ref(false);
const editMode = ref(false);
const router = useRouter()
const editedCourse = ref({
  courseTitle: '',
  teacher:'',
  startDate: '',
  description: '',
  image: '',
  maxAttendants: '',
  price: '',
});
const valid = ref(true);
const setErrorMsg = (msg) => {
  errorMsg.value = msg;
};

watchEffect(() => {
  courses.value = courseStore.courses;
});

onMounted(() => {
 courseStore.showCourses()
  console.log('comp mounted')
  fetchCourses();
});

const fetchCourses = async () => {
  await courseStore.showCourses();
  courses.value = courseStore.courses;
};

const openDialog = () => {
  editMode.value = false;
  editedCourse.value = { courseTitle: '',teacher:'', startDate: '', description: '', image: '', maxAttendants:'',price:'' };
  dialog.value = true;
};

const editCourse = (course) => {
  editMode.value = true;
  editedCourse.value = { ...course };
  dialog.value = true;
};

const saveCourse = async () => {
  try {
    console.log('Before update/create Course');
    if (editMode.value) {
      console.log('update Course called');
      console.log(editedCourse.value.courseId);
      await courseStore.updateCourse(editedCourse.value.courseId, editedCourse.value);
     // await courseStore.showCourses()
    } else {
      console.log('create Course called');
      console.log(editedCourse.value.courseId);
      await courseStore.createCourse(editedCourse.value);
      //await courseStore.showCourses()
    }
    console.log('Before showCourses');
    await courseStore.showCourses();
    console.log('After showCourses');
  } catch (error) {
    console.error('Error saving course:', error);
  } finally {
    closeDialog();
  }
};


async function deleteCourse(courseId){
  console.log('courseId delete' ,courseId)
  await courseStore.deleteCourse(courseId)
  await courseStore.showCourses()
}

const closeDialog = () => {
  dialog.value = false;
  valid.value = true;
  editedCourse.value = {
    courseTitle: '',
    teacher: '',
    startDate: '',
    description: '',
    image: '',
    maxAttendants: '',
    price: '',
  };
};

const openAdminCourseUserView = (courseId) => {
  router.push({ name: 'adminCourseUserView' ,params: {courseId} });
};
</script>

<template>
  <v-container>
    <v-row>
      <v-card @click="openDialog" class="add-course-card ma-3" width="350">
        <v-img
            class="align-end text-white"
            height="250"
            src="data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBwgHBgkIBwgKCgkLDRYPDQwMDRsUFRAWIB0iIiAdHx8kKDQsJCYxJx8fLT0tMTU3Ojo6Iys/RD84QzQ5OjcBCgoKDQwNGg8PGjclHyU3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3N//AABEIAJQAlAMBIgACEQEDEQH/xAAbAAEAAwEBAQEAAAAAAAAAAAAAAgQFAwEHBv/EADQQAAIBAgMFBgUCBwAAAAAAAAABAgMEERJSBRMhMZI0QVGBkbFUYWJxoULRFCIkY3Lh8P/EABQBAQAAAAAAAAAAAAAAAAAAAAD/xAAUEQEAAAAAAAAAAAAAAAAAAAAA/9oADAMBAAIRAxEAPwD6Nnnrl1DPPXLqIgCWeeuXUM89cuoiAJZ565dQzz1y6iIAlnnrl1DPPXLqIgCWeeuXUM89cuoiAJZ565dQzz1y6iIAlnnrl1DPPXLqIgCWeeuXUM89cuoiAJZ565dQzz1y6iIAlnnrl1Hh4AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAegeAlGMpvCEXJ/JHenY1580oL6mBWBZurSVvCMnNSxeDwXIrAAAAAAAAAAAAAAAFq0s5XEXPOoxxw5Yss0rW0VRQz7yXg3+wGYuLwS4+B3p2lxU5Uml4y4GqnQofyp06b8MUiFSpTnyu8q+mUQKsdnZVjWqqK70v3Pf6Cj/AHH6nsra0m8ZXUpP5zR5/CWfxL6ogHtCWVqjRwivly9CtUvLipzqvD6eBqU6lvSpxhCrTSX1LiVZ21nOblv8MXjgpLACWLudmNyeMo8381/ozDXt/wCGowcY14tS7nJHKFjbTeEK7b8FJAZoOlxT3VedNPFRZzAAAAAAAAAAADQ2VLHeU390crGO7v8ALpzL8ELGe7uoPx/lLjhl2rGXdKLf4wAp7Q43lTy9itgWdodtq+XsLS1lcOXFxiv1Yd4FbAYHWvRqUJ5aiw8H3M5geYF6xs41oOpVbwTwSRSLFtdzt00kpRfHBgL63VvUWVtxkuGPMlszta/xZxua87ieaXDhgku467M7XH7MDy/7ZU8vYrli/wC2VPL2K4AAAAAAAAAAAe4tNNc1yNrhUlQrLwf5RiGvs2ee1y6W1/3qBQv03fVFHm2kvQ1LaiqFFQ7/ANXzZVjBS2tUx7kn+C/iBGrShVg4TjivY4WtnChOUsc+PBJrkiyAKN1YKTc6GCenuM2ScJZZrBruZ+gOVehTrxwmuPc1zQGGWdm9sj9mRubWpbvGWDh3SRPZvbI/ZgRv+2VPL2K5Yv8AtlTy9iuAAAAAAAAAAAAvbKnhVnB/qWK8iiep4PFcH4gala0qyuXWpVVFsbi9+KM3eVNcvUbyeuXqBpbi9+KG4vfijN3k9cvUbyeuXqBpbi9+KG4vfijN3k9cvUbyeuXqBou3vGmncpp81gLSxlQqqcpp4J8EjO3k9cvUbyeuXqB1vu2VfuvYrnp4AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAf/2Q=="
            cover
        ></v-img>

        <v-card-title>Estelle einen neuen Kurs</v-card-title>
        <v-card-text class="text-center">

          <v-icon size="48">mdi-plus</v-icon>
        </v-card-text>
      </v-card>

      <v-col v-for="course in courses" :key="course.id" cols="12" md="4">
        <v-card width="350">
          <v-img
              cover
              height="250"
              :src="course.image"
          ></v-img>

          <v-card-title>{{ course.courseTitle }}</v-card-title>
          <v-card-subtitle>{{ course.startDate }}</v-card-subtitle>
          <v-card-text>{{ course.teacher }}</v-card-text>
          <v-card-actions>
            <v-btn
                variant="plain"
                size="large"
                :style="{ color: __colors.primary }"
            >
              Details
            </v-btn>

            <v-spacer></v-spacer>

            <v-btn
                :icon="show ? 'mdi-chevron-up' : 'mdi-chevron-down'"
                @click="show = !show"
            ></v-btn>
          </v-card-actions>

          <v-expand-transition>
            <div v-show="show">
              <v-divider></v-divider>

              <v-card-text>
                {{course.description}}
              </v-card-text>
            </div>
          </v-expand-transition>
          <v-card-actions>


            <v-btn @click="editCourse(course)" icon>
              <v-icon>mdi-pencil</v-icon>
            </v-btn>
            <v-btn @click="deleteCourse(course.courseId)" icon="mdi-delete" color="orange" >

            </v-btn>
            <v-btn @click="openAdminCourseUserView(course.courseId)" icon>
              <v-icon>mdi-account-plus</v-icon>
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </v-container>


  <v-dialog v-model="dialog" max-width="500">
    <v-card>
      <v-card-title>{{ editMode ? 'Bearbeite deinen Kurs' : 'Erstelle einen neuen Kurs' }}</v-card-title>
      <v-card-text>
        <v-form ref="form" v-model="valid" lazy-validation>
          <v-text-field v-model="editedCourse.courseTitle" label="Kurs Titel" hint="required" required></v-text-field>
          <v-text-field v-model="editedCourse.teacher" label="Kochmentor" ></v-text-field>
          <v-text-field v-model="editedCourse.startDate" label="Kursbeginn" hint="yyyy-MM-dd HH:mm:ss" ></v-text-field>
          <v-text-field v-model="editedCourse.description" label="Beschreibung" ></v-text-field>
          <v-text-field v-model="editedCourse.image" label="Bild URL" ></v-text-field>
          <v-text-field v-model.number="editedCourse.maxAttendants" label="Max.Teilnehmer Anzahl"></v-text-field>
          <v-text-field v-model.number="editedCourse.price" label="Preis"></v-text-field>
          <v-alert v-if="errorMsg" closable text="..." type="error" variant="tonal">{{errorMsg}}</v-alert>

        </v-form>
      </v-card-text>
      <v-card-actions>
        <v-btn @click="saveCourse" :disabled="!valid" class="ma-2 elevation-2">Speichern</v-btn>
        <v-btn @click="closeDialog" variant="tonal" class="ma-2">Zurück</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>


</template>
<style scoped>

</style>



