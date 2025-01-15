export const imageToSvg = () => {
  document.querySelectorAll("img.fn__svg").forEach((el) => {
    const imgID = el.getAttribute("id");
    const imgClass = el.getAttribute("class");
    const imgURL = el.getAttribute("src");

    if (!imgURL) {
      console.error("A imagem não possui uma URL válida:", el);
      return;
    }

    fetch(imgURL)
      .then((data) => data.text())
      .then((response) => {
        const parser = new DOMParser();
        const xmlDoc = parser.parseFromString(response, "text/html");
        const svg = xmlDoc.querySelector("svg");

        if (!svg) {
          console.error("O SVG não foi encontrado na URL:", imgURL);
          return;
        }

        if (typeof imgID !== "undefined" && imgID) {
          svg.setAttribute("id", imgID);
        }

        if (typeof imgClass !== "undefined" && imgClass) {
          svg.setAttribute("class", imgClass + " replaced-svg");
        }

        svg.removeAttribute("xmlns:a");
        if (el.parentNode) {
          el.parentNode.replaceChild(svg, el);
        }
      })
      .catch((error) => {
        console.error("Erro ao buscar ou processar o SVG:", error);
      });
  });
};

export const animationText = () => {
  const fn_animated_text = document.querySelectorAll(".fn__animated_text");
  fn_animated_text.forEach((text) => {
    let letters = text.innerHTML.split(""),
      time = text.getAttribute("wait"),
      speed = text.getAttribute("speed");
    if (!time) {
      time = 0;
    }
    if (!speed) {
      speed = 4;
    }
    speed = speed / 100;
    text.innerHTML = "<em>321...</em>";
    text.classList.add("ready");
    if (typeof window !== "undefined") {
      require("waypoints/lib/noframework.waypoints.min.js");
      var waypoint = new Waypoint({
        element: text,
        handler: function () {
          if (!text.classList.contains("stop")) {
            text.classList.add("stop");
            setTimeout(() => {
              text.innerHTML = "";
              letters.forEach((i, e) => {
                var span = document.createElement("span");
                span.textContent = i;
                span.style.animationDelay = e * speed + "s";
                text.append(span);
              });
            }, time);
          }
        },

        offset: "90%",
      });
    }
  });
};
