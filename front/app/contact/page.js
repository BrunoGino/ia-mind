import Contact from '@/components/Contact'
import Layout from '@/layouts/layout'
import React from 'react'

export const metadata = {
  title:'Contact',
  content:'text/html',
  openGraph: {
    title:'Contact',
    content:'text/html',
  },
}


export default function page() {
  return (
    <Layout>
      <Contact />
    </Layout>
  )
}
