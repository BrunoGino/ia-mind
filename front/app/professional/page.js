import ProfessionalsForm from '@/components/ProfessionalsForm'
import Layout from '@/layouts/layout'
import React from 'react'

export const metadata = {
  title:'ProfessionalsForm',
  content:'text/html',
  openGraph: {
    title:'ProfessionalsForm',
    content:'text/html',
  },
}

export default function page() {
  return (
    <Layout>
      <ProfessionalsForm />
    </Layout>
  )
}
