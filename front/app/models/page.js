import Models from '@/components/Models'
import Layout from '@/layouts/layout'
import React from 'react'

export const metadata = {
  title:'Models',
  content:'text/html',
  openGraph: {
    title:'Models',
    content:'text/html',
  },
}

export default function page() {
  return (
    <Layout>
      <Models />
    </Layout>
  )
}
