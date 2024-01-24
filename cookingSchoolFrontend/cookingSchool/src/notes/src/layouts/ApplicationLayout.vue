<template>
  <VAppBar>
    <VAppBarTitle>Songliste</VAppBarTitle>
    <VToolbarItems>
      <VBtn to="/dashboard">Home</VBtn>
      <VBtn to="/new-song">Neues Lied hinzufügen</VBtn>
    </VToolbarItems>
  </VAppBar>
  <VNavigationDrawer>
    Ich bin ein Seitenmenü
  </VNavigationDrawer>
  <VMain>
    <RouterView/>
  </VMain>
</template>
