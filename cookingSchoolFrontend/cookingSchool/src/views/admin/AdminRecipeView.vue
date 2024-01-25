<script setup>
import {useRecipeStore} from "@/stores/RecipeStore";
import {useRouter} from "vue-router";
import {onMounted, ref} from "vue";
import {useCourseStore} from "@/stores/CourseStore";
const courseStore =useCourseStore()
const recipeStore = useRecipeStore()
const recipes = ref([]);
const dialog = ref(false);
const editMode = ref(false);
const recipeData = ref({
  title: '',
  description: '',
  difficulty: 1,
  preparation: 0,
  selectedCourses: [],
  ingredients: [
    {title: '', quantity: '', unit: ''}
  ]
})
const courseOptions = ref([])

onMounted(() => {
  recipeStore.showRecipes()
  loadCourses()
  fetchRecipes()

});


const loadCourses = async () => {
  try {
    console.log('Before loading courses');
    await courseStore.showCourses();
    console.log('After loading courses');

    console.log('Courses loaded:', courseStore.courses);

    courseOptions.value = courseStore.courses.map(course => ({
      courseId: course.courseId,
      title: course.courseTitle
    }));

    console.log('courseOptions:', courseOptions.value);
    console.log('courseID for recipes loaded');
  } catch (err) {
    console.error("Error loading courses:", err);
  }
};

const valid = ref(true)

const addIngredientRow = () => {
  if (!recipeData.value.ingredients) {
    recipeData.value.ingredients = []
  }
  recipeData.value.ingredients.push({ title: '', quantity: '', unit: '' });
};

const fetchRecipes= async () =>{
  await recipeStore.showRecipes()
  recipes.value = recipeStore.recipes;
}

const openDialog = () => {
  editMode.value = false;
 recipeData.value = {
    title: '',
    description: '',
    difficulty: 1,
    preparation: 0,
    selectedCourses: [],
    ingredients: [
      {title: '', quantity: '', unit: ''}
    ]
  }
  dialog.value = true;
};

const editRecipe = (recipe) => {
  editMode.value = true;
  recipeData.value = { ...recipe };
  dialog.value = true;
};
const saveRecipe = async () => {
  try {
    console.log('Selected Courses:', recipeData.value.selectedCourses);
    console.dir(recipeData.value.selectedCourses);
    let courseIds = [];
    courseIds.push(recipeData.value.selectedCourses);

    console.log('Before update/create Recipe');
    if (editMode.value) {
      console.log('update Recipe called');
      console.log(recipeData.value.recipeId);

      await recipeStore.updateRecipe(recipeData.value.recipeId, {
        title: recipeData.value.title,
        description: recipeData.value.description,
        difficulty: recipeData.value.difficulty,
        preparation: recipeData.value.preparation,
        courseIds: courseIds,
        ingredients: recipeData.value.ingredients
      });
    } else {
      console.log('create Recipe called');

      // Use the formatted recipeDataToSend
      await recipeStore.addRecipe({
        title: recipeData.value.title,
        description: recipeData.value.description,
        difficulty: recipeData.value.difficulty,
        preparation: recipeData.value.preparation,
        courseIds: courseIds,
        ingredients: recipeData.value.ingredients
      });
    }

    console.log('Before showRecipes');
    await recipeStore.showRecipes();
    console.log('After showRecipes');
  } catch (error) {
    console.error('Error saving recipe:', error);
  } finally {
    closeDialog();
  }
};

async function deleteRecipe(recipeId){
  console.log('recipeId delete' ,recipeId)
  await recipeStore.deleteRecipe(recipeId)
  await recipeStore.showRecipes()
}
const closeDialog = () => {
  dialog.value = false;
  valid.value = true;
  recipeData.value = {
    title: '',
    description: '',
    difficulty: 1,
    preparation: 0,
    selectedCourses: [],
    ingredients: [
      {title: '', quantity: '', unit: ''}
    ]
  };
};


</script>
  <template>

     <v-container>
       <v-row>
         <v-col v-for="recipe in recipes" :key="recipe.id" cols="12" md="4">
           <v-card>
             <v-card-title>{{recipe.title}}</v-card-title>
             <v-card-subtitle>Difficulty: {{recipe.difficulty}} </v-card-subtitle>
             <v-card-subtitle> Preparation: {{recipe.preparation}}</v-card-subtitle>
             <v-card-subtitle> Kurs Ids: {{recipe.courseIds}}</v-card-subtitle>
             <v-card-text>
               Zutaten:
               <ul>
                 <li v-for="(ingredient, index) in recipeData.ingredients" :key="index">
                   {{ ingredient.quantity }} - {{ ingredient.unit }} - {{ ingredient.title }}
                 </li>
               </ul>
             </v-card-text>
             <v-card-text>Beschreibung: {{recipe.description}}</v-card-text>
             <v-card-actions>
               <v-btn icon="mdi-pencil" size="small" @click="editRecipe(recipe)"></v-btn>
               <v-btn icon="mdi-delete" size="small" @click="deleteRecipe(recipe.recipeId)"></v-btn>
             </v-card-actions>
           </v-card>
         </v-col>
       </v-row>
     </v-container>

      <v-dialog v-model="dialog" max-width="600">
        <v-card>
        <v-card-title>{{ editMode ? 'Edit Recipe' : 'Add New recipe' }}</v-card-title>
        <v-card-text>
          <v-form ref="form" v-model="valid" lazy-validation>

            <!-- Titel -->
            <v-row>
              <v-col>
                <v-text-field v-model="recipeData.title" label="Titel"></v-text-field>
              </v-col>

              <!-- Kurs -->

             <!-- <v-col>
                <v-select v-model="recipeData.selectedCourses" :items="courseOptions" label="Kurs" item-value="courseId"></v-select>
                <div>{{ recipeData.selectedCourses }}</div>
              </v-col>
            </v-row> -->
              <v-col>
                <v-select
                    v-model="recipeData.selectedCourses"
                    :items="courseOptions"
                    label="Kurse"
                    item-value="courseId"
                ></v-select>

              </v-col>

            </v-row>

              <!-- Beschreibung -->
            <v-row>
              <v-col>
                <v-textarea v-model="recipeData.description" label="Beschreibung" rows="5"></v-textarea>
              </v-col>
            </v-row>

            <!-- Schwierigkeitsgrad -->
            <v-row>
              <v-col>
                <v-text-field v-model="recipeData.difficulty" label="Schwierigkeitsgrad" type="number"></v-text-field>
              </v-col>


              <!-- Vorbereitungszeit -->

              <v-col>
                <v-text-field v-model="recipeData.preparation" label="Vorbereitungszeit (in Minuten)" type="number"></v-text-field>
              </v-col>
            </v-row>

            <!-- Zutaten -->
            <v-row v-for="(ingredient, index) in recipeData.ingredients" :key="index">
              <v-col>
                <v-text-field v-model="ingredient.title" label="Zutat"></v-text-field>
              </v-col>
              <v-col>
                <v-text-field v-model="ingredient.quantity" label="Menge"></v-text-field>
              </v-col>
              <v-col>
                <v-text-field v-model="ingredient.unit" label="Einheit"></v-text-field>
              </v-col>
            </v-row>

            <!-- Neue Zutat hinzufügen -->
            <v-row>
              <v-col>
                <v-btn @click="addIngredientRow">Neue Zutat hinzufügen</v-btn>
              </v-col>
            </v-row>



            <!-- Zutatenliste -->
            <v-row>
              <v-col>
                <div>
                  Zutatenliste:
                  <ul>
                    <li v-for="(ingredient, index) in recipeData.ingredients" :key="index">
                      {{ ingredient.quantity }} - {{ ingredient.unit }} - {{ ingredient.title }}
                    </li>
                  </ul>
                </div>
              </v-col>
            </v-row>

            <!-- Submit-Button -->
            <v-row>

            </v-row>

          </v-form>
        </v-card-text>
        <v-card-actions>
          <v-btn @click="saveRecipe" :disabled="!valid">Save</v-btn>
          <v-btn @click="closeDialog">Cancel</v-btn>
        </v-card-actions>
        </v-card>
      </v-dialog>
      <v-btn @click="openDialog">Add New Recipe</v-btn>


</template>

<style scoped>

</style>
