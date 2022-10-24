<template>
  <div id="app">
    <nav>
      <router-link to="/">Main</router-link> | 
      <router-link to="/login">Login</router-link>
    </nav>
    <router-view/>
  </div>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
  font-size: 1.3rem;
  text-decoration-line: none;
}

nav a.router-link-exact-active {
  color: slateblue;
}
</style>
