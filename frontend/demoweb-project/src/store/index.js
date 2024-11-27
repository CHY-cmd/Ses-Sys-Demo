import { createStore } from "vuex";

export default createStore({
  state: {
    snackbar: {
      show: false,
      color: "",
      message: "",
      timeout: 5000,
    },
    username: "",
    isLoggedIn: false,
  },
  getters: {
    getUsername: (state) => state.username,
  },
  mutations: {
    UPDATE_SNACKBAR(state, payload) {
      state.snackbar = { ...state.snackbar, ...payload };
    },
    setUsername(state, username) {
      state.username = username; //后端返回的username
    },
    login(state) {
      state.isLoggedIn = true;
    },
    logout(state) {
      state.isLoggedIn = false;
    },
  },
  actions: {
    updateSnackbar({ commit }, payload) {
      commit("UPDATE_SNACKBAR", payload);
    },
    
  },
  modules: {},
});
