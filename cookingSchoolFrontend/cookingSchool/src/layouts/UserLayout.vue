<script setup>
import { RouterLink, RouterView } from 'vue-router'
import HeaderIcon from '@/components/HeaderIcon.vue'

//TODO: Kathy - Add logout function and link to guest home view
//TODO: Kathy - change navbar to toolbar
</script>

<template>
  <!-- Grundgerüst des Headers und Navbar für die Ansicht der User -->
  <HeaderIcon/>
  <div class="container">
      <div class="item">
        <nav>
            <v-btn to="/user" ><v-icon>home</v-icon></v-btn>
        </nav>
      </div>
      <div class="item">
        <nav>
        <RouterLink to="/user/courses">Kurse</RouterLink>
        </nav>
      </div>
      <div class="item">
        <nav>
        <RouterLink to="/user/recipes">Rezepte</RouterLink>
        </nav>
      </div>
      <div class="item">
        <nav>
        <RouterLink to="/user/about">About Us</RouterLink>
        </nav>
      </div>
      <div class="item">
        <nav>
          <RouterLink to="/user/about">Logout</RouterLink>
        </nav>
      </div>
    </div>
  <RouterView />
</template>


<style scoped>
.item {
  width: 100%;
  font-size: 1.2rem;
  font-weight: 500;
  text-decoration: none;
  margin-bottom: 0.4rem;
  color: var(--color-text);
  margin-right: 12px;
  margin-left: 12px;
}

.container {
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  align-items: center;
  border: 2px solid black;
  max-width: 100%;
}

.dropdown {
  background-color: transparent;
  border: none;
  font-size: 1.2rem;
  font-weight: 500;
  text-decoration: none;
  color: var(--color-text);
  padding: 10px 20px;
  text-align: center;
  display: inline-block;
}

</style>
