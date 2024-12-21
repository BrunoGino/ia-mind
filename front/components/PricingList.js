
"use client"
import React, { useState } from 'react'
import Link from 'next/link'


export default function PricingList() {
  const [activeIndex, setActiveIndex] = useState(1);
  const handleOnClick = (index) => {
    setActiveIndex(index);
  };
  return (
    <>
      {/* toggle pricing */}
      <div className="pricing__toggle">
        <div className="toggle_in">
          <a onClick={() => handleOnClick(1)}  className={activeIndex === 1 ? "active" : ""}>Yearly</a>
          <a onClick={() => handleOnClick(2)}  className={activeIndex === 2 ? " active" : ""}>Monthly</a>
          <span className="bg" style={activeIndex===1 ? {"left":"10px","width":"118.875px"} : {"left":"128.875px","width":"134.594px"}} />
        </div>
      </div>
      {/* !toggle pricing */}
      {/* pricing tabs */}
      <div className="pricing__tabs">
        <div className={activeIndex === 1 ? "pricing__tab active" : "pricing__tab"} id="tab1">
          {/* Mobile Pricing Table (shortcode) */}
          <div className="fn__mobile_pricing">
            {/* First Plan */}
            <div className="pricing__item">
              <div className="pricing__item_holder">
                <div className="pricing__item__header">
                  <h2 className="title">Personal</h2>
                  <h3 className="price"><span>$10</span> / month</h3>
                  <p className="desc">billed yearly<br /><span>$15.00</span> billed monthly</p>
                  <p className="purchase">
                    <Link href="#" className="techwave_fn_button"><span>Buy Personal</span></Link>
                  </p>
                </div>
                <div className="pricing__item__heading">
                  <h2 className="title">Main Features</h2>
                </div>
                <div className="pricing__item_list">
                  <div className="pricing__item_list_item">
                    <h4 className="title">Tokens per month</h4>
                    <p className="desc">5,500</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Upscales per month</h4>
                    <p className="desc">1,500</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Background removals</h4>
                    <p className="desc">4,000</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Pending jobs</h4>
                    <p className="desc">+</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Private generations</h4>
                    <p className="desc">+</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Priority infrastructure</h4>
                    <p className="desc">—</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Relaxed generation</h4>
                    <p className="desc">—</p>
                  </div>
                </div>
                <div className="pricing__item__heading">
                  <h2 className="title">Other Features</h2>
                </div>
                <div className="pricing__item_list">
                  <div className="pricing__item_list_item">
                    <h4 className="title">Essential Frenify models</h4>
                    <p className="desc">+</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Expanded Frenify models</h4>
                    <p className="desc">+</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Community models</h4>
                    <p className="desc">—</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Custom Model</h4>
                    <p className="desc">—</p>
                  </div>
                </div>
                <div className="pricing__item_footer">
                  <Link href="#" className="techwave_fn_button"><span>Buy Personal</span></Link>
                </div>
              </div>
            </div>
            {/* !First Plan */}
            {/* Second Plan */}
            <div className="pricing__item">
              <div className="pricing__item_holder">
                <div className="popular"><span>Most Popular</span></div>
                <div className="pricing__item__header">
                  <h2 className="title">Premium</h2>
                  <h3 className="price"><span>$15</span> / month</h3>
                  <p className="desc">billed yearly<br /><span>$20.00</span> billed monthly</p>
                  <p className="purchase">
                    <Link href="#" className="techwave_fn_button"><span>Buy Premium</span></Link>
                  </p>
                </div>
                <div className="pricing__item__heading">
                  <h2 className="title">Main Features</h2>
                </div>
                <div className="pricing__item_list">
                  <div className="pricing__item_list_item">
                    <h4 className="title">Tokens per month</h4>
                    <p className="desc">12,500</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Upscales per month</h4>
                    <p className="desc">3,500</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Background removals</h4>
                    <p className="desc">8,000</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Pending jobs</h4>
                    <p className="desc">+</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Private generations</h4>
                    <p className="desc">+</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Priority infrastructure</h4>
                    <p className="desc">+</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Relaxed generation</h4>
                    <p className="desc">—</p>
                  </div>
                </div>
                <div className="pricing__item__heading">
                  <h2 className="title">Other Features</h2>
                </div>
                <div className="pricing__item_list">
                  <div className="pricing__item_list_item">
                    <h4 className="title">Essential Frenify models</h4>
                    <p className="desc">+</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Expanded Frenify models</h4>
                    <p className="desc">+</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Community models</h4>
                    <p className="desc">+</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Custom Model</h4>
                    <p className="desc">—</p>
                  </div>
                </div>
                <div className="pricing__item_footer">
                  <Link href="#" className="techwave_fn_button"><span>Buy Premium</span></Link>
                </div>
              </div>
            </div>
            {/* !Second Plan */}
            {/* Third Plan */}
            <div className="pricing__item">
              <div className="pricing__item_holder">
                <div className="pricing__item__header">
                  <h2 className="title">Enerprice</h2>
                  <h3 className="price"><span>$25</span> / month</h3>
                  <p className="desc">billed yearly<br /><span>$35.00</span> billed monthly</p>
                  <p className="purchase">
                    <Link href="#" className="techwave_fn_button"><span>Buy Enterprice</span></Link>
                  </p>
                </div>
                <div className="pricing__item__heading">
                  <h2 className="title">Main Features</h2>
                </div>
                <div className="pricing__item_list">
                  <div className="pricing__item_list_item">
                    <h4 className="title">Tokens per month</h4>
                    <p className="desc">20,000</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Upscales per month</h4>
                    <p className="desc">7,500</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Background removals</h4>
                    <p className="desc">15,000</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Pending jobs</h4>
                    <p className="desc">+</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Private generations</h4>
                    <p className="desc">+</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Priority infrastructure</h4>
                    <p className="desc">+</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Relaxed generation</h4>
                    <p className="desc">+</p>
                  </div>
                </div>
                <div className="pricing__item__heading">
                  <h2 className="title">Other Features</h2>
                </div>
                <div className="pricing__item_list">
                  <div className="pricing__item_list_item">
                    <h4 className="title">Essential Frenify models</h4>
                    <p className="desc">+</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Expanded Frenify models</h4>
                    <p className="desc">+</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Community models</h4>
                    <p className="desc">+</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Custom Model</h4>
                    <p className="desc">+</p>
                  </div>
                </div>
                <div className="pricing__item_footer">
                  <Link href="#" className="techwave_fn_button"><span>Buy Enterpice</span></Link>
                </div>
              </div>
            </div>
            {/* !First Plan */}
          </div>
          {/* /Mobile Pricing Table (shortcode) */}
          <div className="pricing__content">
            {/* table's header */}
            <div className="pricing__header">
              <div className="item_row">
                <div className="item_col" />
                <div className="item_col">
                  <h2 className="title">Personal</h2>
                  <h3 className="price"><span>$10</span> / month</h3>
                  <p className="desc">billed yearly<br /><span>$15.00</span> billed monthly</p>
                  <p className="purchase">
                    <Link href="#" className="techwave_fn_button"><span>Buy Personal</span></Link>
                  </p>
                </div>
                <div className="item_col">
                  <div className="popular"><span>Most Popular</span></div>
                  <h2 className="title">Premium</h2>
                  <h3 className="price"><span>$15</span> / month</h3>
                  <p className="desc">billed yearly<br /><span>$20.00</span> billed monthly</p>
                  <p className="purchase">
                    <Link href="#" className="techwave_fn_button"><span>Buy Premium</span></Link>
                  </p>
                </div>
                <div className="item_col">
                  <h2 className="title">Enterprice</h2>
                  <h3 className="price"><span>$25</span> / month</h3>
                  <p className="desc">billed yearly<br /><span>$35.00</span> billed monthly</p>
                  <p className="purchase">
                    <Link href="#" className="techwave_fn_button"><span>Buy Enterprice</span></Link>
                  </p>
                </div>
              </div>
            </div>
            {/* !table's header */}
            {/* table's heading */}
            <div className="pricing__heading">
              <div className="item"><span className="title">Main Features</span></div>
              <div className="item wide" />
            </div>
            {/* !table's heading */}
            {/* table's options */}
            <div className="pricing__fields">
              <div className="item_row">
                <div className="item_col">
                  <span className="heading_text">Tokens per month</span>
                </div>
                <div className="item_col">
                  <span className="option_text">5,500</span>
                </div>
                <div className="item_col">
                  <span className="option_text">12,500</span>
                </div>
                <div className="item_col">
                  <span className="option_text">20,000</span>
                </div>
              </div>
              <div className="item_row">
                <div className="item_col">
                  <span className="heading_text">Upscales per month</span>
                </div>
                <div className="item_col">
                  <span className="option_text">1,500</span>
                </div>
                <div className="item_col">
                  <span className="option_text">3,500</span>
                </div>
                <div className="item_col">
                  <span className="option_text">7,500</span>
                </div>
              </div>
              <div className="item_row">
                <div className="item_col">
                  <span className="heading_text">Background removals</span>
                </div>
                <div className="item_col">
                  <span className="option_text">4,000</span>
                </div>
                <div className="item_col">
                  <span className="option_text">8,000</span>
                </div>
                <div className="item_col">
                  <span className="option_text">15,000</span>
                </div>
              </div>
              <div className="item_row">
                <div className="item_col">
                  <span className="heading_text">Pending jobs</span>
                </div>
                <div className="item_col">
                  <span className="option_text">+</span>
                </div>
                <div className="item_col">
                  <span className="option_text">+</span>
                </div>
                <div className="item_col">
                  <span className="option_text">+</span>
                </div>
              </div>
              <div className="item_row">
                <div className="item_col">
                  <span className="heading_text">Private generations</span>
                </div>
                <div className="item_col">
                  <span className="option_text">+</span>
                </div>
                <div className="item_col">
                  <span className="option_text">+</span>
                </div>
                <div className="item_col">
                  <span className="option_text">+</span>
                </div>
              </div>
              <div className="item_row">
                <div className="item_col">
                  <span className="heading_text">Priority infrastructure</span>
                </div>
                <div className="item_col">
                  <span className="option_text">—</span>
                </div>
                <div className="item_col">
                  <span className="option_text">+</span>
                </div>
                <div className="item_col">
                  <span className="option_text">+</span>
                </div>
              </div>
              <div className="item_row">
                <div className="item_col">
                  <span className="heading_text">Relaxed generation</span>
                </div>
                <div className="item_col">
                  <span className="option_text">—</span>
                </div>
                <div className="item_col">
                  <span className="option_text">—</span>
                </div>
                <div className="item_col">
                  <span className="option_text">+</span>
                </div>
              </div>
            </div>
            {/* !table's options */}
            {/* table's heading */}
            <div className="pricing__heading">
              <div className="item"><span className="title">Other Features</span></div>
              <div className="item wide" />
            </div>
            {/* !table's heading */}
            {/* table's options */}
            <div className="pricing__fields">
              <div className="item_row">
                <div className="item_col">
                  <span className="heading_text">Essential Frenify models</span>
                </div>
                <div className="item_col">
                  <span className="option_text">+</span>
                </div>
                <div className="item_col">
                  <span className="option_text">+</span>
                </div>
                <div className="item_col">
                  <span className="option_text">+</span>
                </div>
              </div>
              <div className="item_row">
                <div className="item_col">
                  <span className="heading_text">Expanded Frenify models</span>
                </div>
                <div className="item_col">
                  <span className="option_text">+</span>
                </div>
                <div className="item_col">
                  <span className="option_text">+</span>
                </div>
                <div className="item_col">
                  <span className="option_text">+</span>
                </div>
              </div>
              <div className="item_row">
                <div className="item_col">
                  <span className="heading_text">Community models</span>
                </div>
                <div className="item_col">
                  <span className="option_text">—</span>
                </div>
                <div className="item_col">
                  <span className="option_text">+</span>
                </div>
                <div className="item_col">
                  <span className="option_text">+</span>
                </div>
              </div>
              <div className="item_row">
                <div className="item_col">
                  <span className="heading_text">Custom Model</span>
                </div>
                <div className="item_col">
                  <span className="option_text">—</span>
                </div>
                <div className="item_col">
                  <span className="option_text">—</span>
                </div>
                <div className="item_col">
                  <span className="option_text">+</span>
                </div>
              </div>
            </div>
            {/* !table's options */}
            {/* table's footer */}
            <div className="pricing__footer">
              <div className="item_row">
                <div className="item_col" />
                <div className="item_col">
                  <Link href="#" className="techwave_fn_button"><span>Buy Personal</span></Link>
                </div>
                <div className="item_col">
                  <Link href="#" className="techwave_fn_button"><span>Buy Premium</span></Link>
                </div>
                <div className="item_col">
                  <Link href="#" className="techwave_fn_button"><span>Buy Enterprice</span></Link>
                </div>
              </div>
            </div>
            {/* !table's footer */}
          </div>
        </div>
        <div className={activeIndex === 2 ? "pricing__tab active" : "pricing__tab"} id="tab2">
          {/* Mobile Pricing Table (shortcode) */}
          <div className="fn__mobile_pricing">
            {/* First Plan */}
            <div className="pricing__item">
              <div className="pricing__item_holder">
                <div className="pricing__item__header">
                  <h2 className="title">Personal</h2>
                  <h3 className="price"><span>$15</span> / month</h3>
                  <p className="desc">billed yearly<br /><span>$20.00</span> billed monthly</p>
                  <p className="purchase">
                    <Link href="#" className="techwave_fn_button"><span>Buy Personal</span></Link>
                  </p>
                </div>
                <div className="pricing__item__heading">
                  <h2 className="title">Main Features</h2>
                </div>
                <div className="pricing__item_list">
                  <div className="pricing__item_list_item">
                    <h4 className="title">Tokens per month</h4>
                    <p className="desc">5,500</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Upscales per month</h4>
                    <p className="desc">1,500</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Background removals</h4>
                    <p className="desc">4,000</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Pending jobs</h4>
                    <p className="desc">+</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Private generations</h4>
                    <p className="desc">+</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Priority infrastructure</h4>
                    <p className="desc">—</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Relaxed generation</h4>
                    <p className="desc">—</p>
                  </div>
                </div>
                <div className="pricing__item__heading">
                  <h2 className="title">Other Features</h2>
                </div>
                <div className="pricing__item_list">
                  <div className="pricing__item_list_item">
                    <h4 className="title">Essential Frenify models</h4>
                    <p className="desc">+</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Expanded Frenify models</h4>
                    <p className="desc">+</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Community models</h4>
                    <p className="desc">—</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Custom Model</h4>
                    <p className="desc">—</p>
                  </div>
                </div>
                <div className="pricing__item_footer">
                  <Link href="#" className="techwave_fn_button"><span>Buy Personal</span></Link>
                </div>
              </div>
            </div>
            {/* !First Plan */}
            {/* Second Plan */}
            <div className="pricing__item">
              <div className="pricing__item_holder">
                <div className="popular"><span>Most Popular</span></div>
                <div className="pricing__item__header">
                  <h2 className="title">Premium</h2>
                  <h3 className="price"><span>$20</span> / month</h3>
                  <p className="desc">billed yearly<br /><span>$25.00</span> billed monthly</p>
                  <p className="purchase">
                    <Link href="#" className="techwave_fn_button"><span>Buy Premium</span></Link>
                  </p>
                </div>
                <div className="pricing__item__heading">
                  <h2 className="title">Main Features</h2>
                </div>
                <div className="pricing__item_list">
                  <div className="pricing__item_list_item">
                    <h4 className="title">Tokens per month</h4>
                    <p className="desc">12,500</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Upscales per month</h4>
                    <p className="desc">3,500</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Background removals</h4>
                    <p className="desc">8,000</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Pending jobs</h4>
                    <p className="desc">+</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Private generations</h4>
                    <p className="desc">+</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Priority infrastructure</h4>
                    <p className="desc">+</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Relaxed generation</h4>
                    <p className="desc">—</p>
                  </div>
                </div>
                <div className="pricing__item__heading">
                  <h2 className="title">Other Features</h2>
                </div>
                <div className="pricing__item_list">
                  <div className="pricing__item_list_item">
                    <h4 className="title">Essential Frenify models</h4>
                    <p className="desc">+</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Expanded Frenify models</h4>
                    <p className="desc">+</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Community models</h4>
                    <p className="desc">+</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Custom Model</h4>
                    <p className="desc">—</p>
                  </div>
                </div>
                <div className="pricing__item_footer">
                  <Link href="#" className="techwave_fn_button"><span>Buy Premium</span></Link>
                </div>
              </div>
            </div>
            {/* !Second Plan */}
            {/* Third Plan */}
            <div className="pricing__item">
              <div className="pricing__item_holder">
                <div className="pricing__item__header">
                  <h2 className="title">Enerprice</h2>
                  <h3 className="price"><span>$30</span> / month</h3>
                  <p className="desc">billed yearly<br /><span>$40.00</span> billed monthly</p>
                  <p className="purchase">
                    <Link href="#" className="techwave_fn_button"><span>Buy Enterprice</span></Link>
                  </p>
                </div>
                <div className="pricing__item__heading">
                  <h2 className="title">Main Features</h2>
                </div>
                <div className="pricing__item_list">
                  <div className="pricing__item_list_item">
                    <h4 className="title">Tokens per month</h4>
                    <p className="desc">20,000</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Upscales per month</h4>
                    <p className="desc">7,500</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Background removals</h4>
                    <p className="desc">15,000</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Pending jobs</h4>
                    <p className="desc">+</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Private generations</h4>
                    <p className="desc">+</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Priority infrastructure</h4>
                    <p className="desc">+</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Relaxed generation</h4>
                    <p className="desc">+</p>
                  </div>
                </div>
                <div className="pricing__item__heading">
                  <h2 className="title">Other Features</h2>
                </div>
                <div className="pricing__item_list">
                  <div className="pricing__item_list_item">
                    <h4 className="title">Essential Frenify models</h4>
                    <p className="desc">+</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Expanded Frenify models</h4>
                    <p className="desc">+</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Community models</h4>
                    <p className="desc">+</p>
                  </div>
                  <div className="pricing__item_list_item">
                    <h4 className="title">Custom Model</h4>
                    <p className="desc">+</p>
                  </div>
                </div>
                <div className="pricing__item_footer">
                  <Link href="#" className="techwave_fn_button"><span>Buy Enterpice</span></Link>
                </div>
              </div>
            </div>
            {/* !First Plan */}
          </div>
          {/* /Mobile Pricing Table (shortcode) */}
          <div className="pricing__content">
            {/* table's header */}
            <div className="pricing__header">
              <div className="item_row">
                <div className="item_col" />
                <div className="item_col">
                  <h2 className="title">Personal</h2>
                  <h3 className="price"><span>$15</span> / month</h3>
                  <p className="desc">billed yearly<br /><span>$20.00</span> billed monthly</p>
                  <p className="purchase">
                    <Link href="#" className="techwave_fn_button"><span>Buy Personal</span></Link>
                  </p>
                </div>
                <div className="item_col">
                  <div className="popular"><span>Most Popular</span></div>
                  <h2 className="title">Premium</h2>
                  <h3 className="price"><span>$20</span> / month</h3>
                  <p className="desc">billed yearly<br /><span>$25.00</span> billed monthly</p>
                  <p className="purchase">
                    <Link href="#" className="techwave_fn_button"><span>Buy Premium</span></Link>
                  </p>
                </div>
                <div className="item_col">
                  <h2 className="title">Enterprice</h2>
                  <h3 className="price"><span>$30</span> / month</h3>
                  <p className="desc">billed yearly<br /><span>$40.00</span> billed monthly</p>
                  <p className="purchase">
                    <Link href="#" className="techwave_fn_button"><span>Buy Enterprice</span></Link>
                  </p>
                </div>
              </div>
            </div>
            {/* !table's header */}
            {/* table's heading */}
            <div className="pricing__heading">
              <div className="item"><span className="title">Main Features</span></div>
              <div className="item wide" />
            </div>
            {/* !table's heading */}
            {/* table's options */}
            <div className="pricing__fields">
              <div className="item_row">
                <div className="item_col">
                  <span className="heading_text">Tokens per month</span>
                </div>
                <div className="item_col">
                  <span className="option_text">5,500</span>
                </div>
                <div className="item_col">
                  <span className="option_text">12,500</span>
                </div>
                <div className="item_col">
                  <span className="option_text">20,000</span>
                </div>
              </div>
              <div className="item_row">
                <div className="item_col">
                  <span className="heading_text">Upscales per month</span>
                </div>
                <div className="item_col">
                  <span className="option_text">1,500</span>
                </div>
                <div className="item_col">
                  <span className="option_text">3,500</span>
                </div>
                <div className="item_col">
                  <span className="option_text">7,500</span>
                </div>
              </div>
              <div className="item_row">
                <div className="item_col">
                  <span className="heading_text">Background removals</span>
                </div>
                <div className="item_col">
                  <span className="option_text">4,000</span>
                </div>
                <div className="item_col">
                  <span className="option_text">8,000</span>
                </div>
                <div className="item_col">
                  <span className="option_text">15,000</span>
                </div>
              </div>
              <div className="item_row">
                <div className="item_col">
                  <span className="heading_text">Pending jobs</span>
                </div>
                <div className="item_col">
                  <span className="option_text">+</span>
                </div>
                <div className="item_col">
                  <span className="option_text">+</span>
                </div>
                <div className="item_col">
                  <span className="option_text">+</span>
                </div>
              </div>
              <div className="item_row">
                <div className="item_col">
                  <span className="heading_text">Private generations</span>
                </div>
                <div className="item_col">
                  <span className="option_text">+</span>
                </div>
                <div className="item_col">
                  <span className="option_text">+</span>
                </div>
                <div className="item_col">
                  <span className="option_text">+</span>
                </div>
              </div>
              <div className="item_row">
                <div className="item_col">
                  <span className="heading_text">Priority infrastructure</span>
                </div>
                <div className="item_col">
                  <span className="option_text">—</span>
                </div>
                <div className="item_col">
                  <span className="option_text">+</span>
                </div>
                <div className="item_col">
                  <span className="option_text">+</span>
                </div>
              </div>
              <div className="item_row">
                <div className="item_col">
                  <span className="heading_text">Relaxed generation</span>
                </div>
                <div className="item_col">
                  <span className="option_text">—</span>
                </div>
                <div className="item_col">
                  <span className="option_text">—</span>
                </div>
                <div className="item_col">
                  <span className="option_text">+</span>
                </div>
              </div>
            </div>
            {/* !table's options */}
            {/* table's heading */}
            <div className="pricing__heading">
              <div className="item"><span className="title">Other Features</span></div>
              <div className="item wide" />
            </div>
            {/* !table's heading */}
            {/* table's options */}
            <div className="pricing__fields">
              <div className="item_row">
                <div className="item_col">
                  <span className="heading_text">Essential Frenify models</span>
                </div>
                <div className="item_col">
                  <span className="option_text">+</span>
                </div>
                <div className="item_col">
                  <span className="option_text">+</span>
                </div>
                <div className="item_col">
                  <span className="option_text">+</span>
                </div>
              </div>
              <div className="item_row">
                <div className="item_col">
                  <span className="heading_text">Expanded Frenify models</span>
                </div>
                <div className="item_col">
                  <span className="option_text">+</span>
                </div>
                <div className="item_col">
                  <span className="option_text">+</span>
                </div>
                <div className="item_col">
                  <span className="option_text">+</span>
                </div>
              </div>
              <div className="item_row">
                <div className="item_col">
                  <span className="heading_text">Community models</span>
                </div>
                <div className="item_col">
                  <span className="option_text">—</span>
                </div>
                <div className="item_col">
                  <span className="option_text">+</span>
                </div>
                <div className="item_col">
                  <span className="option_text">+</span>
                </div>
              </div>
              <div className="item_row">
                <div className="item_col">
                  <span className="heading_text">Custom Model</span>
                </div>
                <div className="item_col">
                  <span className="option_text">—</span>
                </div>
                <div className="item_col">
                  <span className="option_text">—</span>
                </div>
                <div className="item_col">
                  <span className="option_text">+</span>
                </div>
              </div>
            </div>
            {/* !table's options */}
            {/* table's footer */}
            <div className="pricing__footer">
              <div className="item_row">
                <div className="item_col" />
                <div className="item_col">
                  <Link href="#" className="techwave_fn_button"><span>Buy Personal</span></Link>
                </div>
                <div className="item_col">
                  <Link href="#" className="techwave_fn_button"><span>Buy Premium</span></Link>
                </div>
                <div className="item_col">
                  <Link href="#" className="techwave_fn_button"><span>Buy Enterprice</span></Link>
                </div>
              </div>
            </div>
            {/* !table's footer */}
          </div>
        </div>
      </div>
      {/* !pricing tabs */}
    </>
  )
}