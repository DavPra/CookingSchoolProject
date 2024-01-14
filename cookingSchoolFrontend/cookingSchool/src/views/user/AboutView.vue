<script setup>
</script>

<template>
  <!-- kurze Beschreibung des Teams und der Location -->
  <div class="">
    <!-- header -->
    <div class="mt-6 d-flex justify-center align-center flex-column border text-center ">
     <v-sheet>
       <h1 class="text-h2 pa-3 "> COOKING</h1>
       <h1 class="text-h4  "> Academy</h1>
      </v-sheet>
    </div>
    <!-- Part 1 -->

    <div class="mt-6   ">
      <v-sheet class="text-subtitle-1 text-center my-4 py-4 ">Willkommen in unserer Kochschule, einem kulinarischen Paradies für alle, die ihre Kochkünste auf das nächste Level bringen und dabei eine unvergessliche Zeit erleben möchten! <br> Inmitten einer angesagten Location, die von urbanem Flair und modernem Design geprägt ist, entfaltet sich unsere fiktive Kochschule zu einem Treffpunkt für Gourmets, Foodies und Kochbegeisterte gleichermaßen.
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
          <v-btn class="align-center ma-4"> Unsere Kurse </v-btn>

        </v-sheet>
        <v-sheet style="flex: 1;">
          <v-img
              class="pa-2 ma-3"
              cover
              height="250"
              src="https://img.freepik.com/fotos-kostenlos/gruppe-freunde-die-in-der-kueche-kochen_53876-2.jpg?w=1800&t=st=1705093561~exp=1705094161~hmac=7c7c99b769c42bc92a0646eb63d041d0ec8c24fa953256f24a0b5af2720031de"
          ></v-img>
        </v-sheet>

      </div>



    </div>
    <!-- Teacher -->
<div class=" mt-2 pa-4 bg-grey-lighten-3">
  <div class=" ma-2 py-5">
    Unser dynamisches Lehrerteam, bestehend aus einem charismatischen Mann und zwei ebenso talentierten Frauen, leitet nicht nur die Kurse, sondern ist auch stolzer Inhaber unserer Kochschule. Jeder Lehrer bringt seine eigene Expertise und Persönlichkeit in den Unterricht ein, um eine inspirierende Lernumgebung zu schaffen.

  </div>
  <div class=" d-flex flex-row">
    <v-card
        class="mx-auto"
        max-width="344"
    >
      <v-img
          src="https://img.freepik.com/fotos-kostenlos/chefkoch-der-frisch-gehackte-gruene-kraeuter-in-die-pfanne-gibt-waehrend-er-gourmetgerichte-in-der-professionellen-kueche-des-restaurants-kocht-arbeiter-in-der-lebensmittelindustrie-bereiten-koestliche-mahlzeiten-mit-bio-gemuese-zu_482257-40135.jpg?w=1800&t=st=1705089846~exp=1705090446~hmac=28d21c62e484fb68685265d26835426bdf7147781b75a9b8761a1e4d3bf42bad"
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
          src="https://img.freepik.com/fotos-kostenlos/attraktives-portraet-des-senior-salatkochens_1409-6883.jpg?w=1800&t=st=1705089671~exp=1705090271~hmac=94d2ceba5ff638cc6d87b7f79cb2dd087cfa6a67181779f21530fc6b57107caa"
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
          src="https://img.freepik.com/fotos-kostenlos/koechin-in-der-kueche-die-eier-in-der-schuessel-trennt_23-2149720740.jpg?w=826&t=st=1705089796~exp=1705090396~hmac=909e57a8862331e1b0634d8edc8adcbee10b05bb554d3326cb58ad8b43da7d29"
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
</div>




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

</style>