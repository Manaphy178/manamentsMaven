// JavaScript para mostrar/ocultar el menú dropdown del usuario
const userIcon = document.getElementById("userIcon");
const userMenu = document.getElementById("userMenu");

userIcon.addEventListener("click", () => {
  userMenu.classList.toggle("show");
});

// Cerrar el menú si se hace clic fuera de él
document.addEventListener("click", (event) => {
  if (!userIcon.contains(event.target) && !userMenu.contains(event.target)) {
    userMenu.classList.remove("show");
  }
});
