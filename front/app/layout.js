import './globals.css'
import 'bootstrap/dist/css/bootstrap-grid.min.css';
import '../public/css/plugins.css'
import '../public/css/style.css'
import { Heebo, Work_Sans } from 'next/font/google'

const heebo = Heebo({
  weight:['100', '200', '300', '400', '500', '600', '700', '800', '900',],
  style:['normal'],
  subsets: ['latin'],
  display: 'swap',
})
const worksans = Work_Sans({
  weight:['100', '200', '300', '400', '500', '600', '700', '800', '900',],
  style:['normal'],
  subsets:['latin'],
  display:'swap',
})


export const metadata = {
  title: {
    template:'IAMind Heath Tech | %s',
    // content:'text/html',
    default:'IAMind | Platafforn For Heath Mental Deases', // a default is required when creating a template
  },
  name: "description",
  content:"IAMind Platafforn For Heath Mental Deases",
  openGraph: {
    title: 'Techwave - React NextJs',
    description: 'IAMind Platafforn For Heath Mental Deases'
  },
  author: [{ name: 'TrendyCoder' }, { name: 'Alam', url: 'https://trendycoder.com' }],
  viewport:'width=device-width, initial-scale=1, maximum-scale=1',
  httpEquiv:'Content-Type',
  charset:'utf-8'
}

export default function RootLayout({ children }) {


  return (
    <html lang='en' className='toggleMenu'>
      <body>
        {children}
      </body>
    </html>

  )
}
