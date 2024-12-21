import FAQ from '@/components/FAQ'
import PricingList from '@/components/PricingList'
import Layout from '@/layouts/layout'
import Link from 'next/link'
import React from 'react'

export const metadata = {
  title:'Pricing',
  content:'text/html',
  openGraph: {
    title:'Pricing',
    content:'text/html',
  },
}

export default function page() {
  return (
    <Layout>
      <div className="techwave_fn_pricing_page">
        <div className="techwave_fn_title_holder">
          <div className="container">
            <h1 className="title">Simple Pricing, Unbeatable Value</h1>
            <p className="desc fn__animated_text">Start small and free, upgrade as you go. Take control of everything.</p>
          </div>
        </div>
        {/* Pricing Shortcode */}
        <div className="techwave_fn_pricing">
          <div className="container">
            <PricingList />
          </div>
        </div>
        {/* !Pricing Shortcode */}
        <div className="techwave_fn_title_holder">
          <div className="container">
            <h1 className="title">Frequently Asked Questions</h1>
            <p className="desc fn__animated_text">Many support queries and technical questions will already be answered</p>
          </div>
        </div>
        <div className="container medium">
          {/* Accordion Shortcode */}
          <FAQ />
          {/* !Accordion Shortcode */}
        </div>
      </div>

    </Layout>
  )
}
