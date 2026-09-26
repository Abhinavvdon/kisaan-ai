/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        soil: {
          light: '#F7F2E9', // warm cream background
          cream: '#EFE8DC',
          border: '#DECDBE',
          DEFAULT: '#6B4423', // soil brown primary
          dark: '#4A2E16',
          text: '#2C1E14'
        },
        leaf: {
          light: '#EAF3E7',
          DEFAULT: '#4C7A3D', // leaf green accent
          hover: '#3F6632',
          dark: '#2E4C24'
        },
        turmeric: {
          light: '#FDF2E9',
          DEFAULT: '#C46A2B', // turmeric orange alert
          dark: '#A6531E'
        },
        sky: {
          light: '#EBF3F7',
          DEFAULT: '#6E97AC', // muted sky blue weather
          dark: '#4F778B'
        }
      },
      fontFamily: {
        sans: ['Inter', 'system-ui', 'sans-serif']
      }
    },
  },
  plugins: [],
}
