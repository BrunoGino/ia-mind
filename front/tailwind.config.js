/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./pages/**/*.{js,ts,jsx,tsx}",
    "./components/**/*.{js,ts,jsx,tsx}",
    "./layouts/**/*.{js,ts,jsx,tsx}",
    "./plugins/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        'primary': '#1a202c', // Ajuste para a cor principal
        'secondary': '#2d3748', // Ajuste para a cor secundária
        'accent': '#4a5568', // Cor de destaque
      },
      fontFamily: {
        sans: ['Inter', 'sans-serif'], // Substitua por fontes desejadas
        heading: ['Roboto', 'sans-serif'],
      },
      spacing: {
        '128': '32rem',
        '144': '36rem',
      },
      borderRadius: {
        '4xl': '2rem',
      },
    },
  },
  plugins: [],
};

