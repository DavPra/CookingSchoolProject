<script setup>
import { useRouter } from 'vue-router'
import HeaderIcon from "@/components/HeaderIcon.vue";

const router = useRouter();

async function goToCourses() {
  await router.push('/courses')
}
</script>

<template>
  <HeaderIcon></HeaderIcon>
  <!-- kurze Beschreibung des Teams und der Location -->
  <div class="">
    <!-- header -->
    <div align-center style="border-bottom: 10px;">
    <v-container>
       <h1 class="text-h2 pa-3 header1"> Cooking Academy</h1>
    </v-container>
    </div>
    <v-spacer></v-spacer>
    <!-- Part 1 -->

    <div class="mt-6   ">
      <v-sheet class="text-subtitle-1 text-center my-4 py-4 ">
        Willkommen in unserer Kochschule, einem kulinarischen Paradies für alle, die ihre Kochkünste auf das nächste Level bringen und dabei eine unvergessliche Zeit erleben möchten!
        <br>
        Inmitten einer angesagten Location, die von urbanem Flair und modernem Design geprägt ist, entfaltet sich unsere fiktive Kochschule zu einem Treffpunkt für Gourmets, Foodies und Kochbegeisterte gleichermaßen.
      </v-sheet>

      <!-- Location Part  -->
      <div class="d-flex justify-start ma-6  align-center">
        <v-sheet style="flex: 1;">
          <v-img
              class="pa-2 ma-3"
              cover
              height="250"
              src="https://cdn.pixabay.com/photo/2017/08/02/13/03/architecture-2571498_1280.jpg"
          ></v-img>
        </v-sheet>
        <v-sheet style="flex: 1;" class="pa-2 ma-3">
          <h4> Inmitten einer angesagten Location, die von urbanem Flair und modernem Design geprägt ist,
            entfaltet sich unsere fiktive Kochschule zu einem Treffpunkt für Gourmets, Foodies und Kochbegeisterte
            gleichermaßen.
          </h4>
        </v-sheet>
      </div>

      <!-- Kurs Part -->
      <div class="d-flex justify-start ma-6  align-center">
        <v-sheet style="flex: 1;" class="pa-2 ma-3">
        <v-sheet>
            Unsere Mission ist es, die Kunst des Kochens mit Leidenschaft, Kreativität und einer gehörigen Portion Spaß zu vermitteln.
            Unabhängig von deinen kulinarischen Fähigkeiten, bieten wir eine breite Palette von Kursen an, die von Anfänger bis Fortgeschrittene reichen.
            Lerne von Grundtechniken bis hin zu raffinierten Gerichten alles, was du für die perfekte Küchenperformance brauchst.
        </v-sheet>
          <v-btn class="align-center ma-4" color="primary" rounded @click = "goToCourses"> Unsere Kurse </v-btn>

        </v-sheet>
        <v-sheet style="flex: 1;">
          <v-img
              class="pa-2 ma-3"
              cover
              height="250"
              src="https://media.gettyimages.com/id/942153866/de/foto/koch-und-teilnehmer-des-kochens-klasse-browser-fotos-auf-handy.jpg?s=612x612&w=0&k=20&c=BaPpBqKZ8yyzsM5Kq3DZ3FePnVKRiEghgxw2sASJWYU="
          ></v-img>
        </v-sheet>

      </div>



    </div>
    <!-- Teacher -->
<v-sheet class=" mt-2 pa-4 " color="secondary">
  <div class=" ma-2 py-5">
    Unser dynamisches Lehrerteam, bestehend aus einem charismatischen Mann und zwei ebenso talentierten Frauen, leitet nicht nur die Kurse, sondern ist auch stolzer Inhaber unserer Kochschule. Jeder Lehrer bringt seine eigene Expertise und Persönlichkeit in den Unterricht ein, um eine inspirierende Lernumgebung zu schaffen.

  </div>
  <div class=" d-flex flex-row">
    <v-card
        class="mx-auto"
        max-width="344"
    >
      <v-img
          src="https://media.gettyimages.com/id/504367131/de/foto/nur-ein-bisschen-geheime-zutat.jpg?s=612x612&w=0&k=20&c=J3MU_YHX_fd8SgO8pivKeIy8lEPLZRZQtQwizMR3M7U="
          height="200px"
          cover
      ></v-img>

      <v-card-title>
        Chef Marco
      </v-card-title>
      <v-card-subtitle>
        Der Geschmackspionier
      </v-card-subtitle>

      <v-card-text>
        Mit einer faszinierenden Mischung aus Tradition und Innovation ist Chef Marco der kreative Kopf unserer Kochschule. Seine kulinarische Leidenschaft und sein feines Gespür für Aromen machen ihn zu einem Experten für exquisiten Genuss. Freu dich darauf, von Chef Marco in die Welt der geschmacklichen Höhepunkte entführt zu werden!
      </v-card-text>
    </v-card>
    <v-card
        class="mx-auto"
        max-width="344"
    >
      <v-img
          src="https://media.gettyimages.com/id/981193812/de/foto/koch-arbeitet-in-einem-restaurant-einen-teller-dekorieren.jpg?s=612x612&w=0&k=20&c=5hZ0FwBywzQWaYFZwdeiz4zH4cm5a8xUxF-x3h3NWyY="
          height="200px"
          cover
      ></v-img>

      <v-card-title>
        Chef Sophie
      </v-card-title>
      <v-card-subtitle>
        Die Küchenkünstlerin
      </v-card-subtitle>

      <v-card-text>
        Chef Sophie ist eine Meisterin der Präzision und der visuellen Ästhetik. Mit ihrer Erfahrung in renommierten Restaurants bringt sie eine elegante Note in unsere Kurse. Ihre Begeisterung für kreative Präsentation und die Liebe zu frischen Zutaten machen ihre Kurse zu einem Fest für die Sinne.
      </v-card-text>
    </v-card>
    <v-card
        class="mx-auto"
        max-width="344"
    >
      <v-img
          src="https://media.gettyimages.com/id/1281800152/de/foto/gl%C3%BCck-steht-immer-auf-der-speisekarte.jpg?s=612x612&w=0&k=20&c=OMksjmN4ZDnylZ0kyDy4gOp0WK05fB6C5u4fAVLwDLg="
          height="200px"
          cover
      ></v-img>

      <v-card-title>
        Chef Laura
      </v-card-title>
      <v-card-subtitle>
        Die Feuerseele
      </v-card-subtitle>

      <v-card-text>
        Chef Laura ist bekannt für ihre lebhafte Persönlichkeit und ihre Fähigkeit, Begeisterung fürs Kochen zu wecken. Als Expertin für Grillen und feurige Aromen bringt sie die Energie in unsere Küche. Ihre Kurse sind nicht nur lehrreich, sondern auch ein Erlebnis voller Spaß und Euphorie.
      </v-card-text>
    </v-card>
  </div>
</v-sheet>
  </div>

</template>

<style scoped>
.text {
  margin-top: 2rem;
  display: flex;
  flex: 1;
  margin-left: 1rem;
}

h3 {
  font-size: 1.5rem;
  font-weight: 500;
  margin-bottom: 0.4rem;
  color: var(--color-heading);
}

.header1 {
  color: #CB5400;
  border-bottom: #CB5400;
  border-bottom-width: 10px;
  align-items: center;
  text-align: center;
}

</style>