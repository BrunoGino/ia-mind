import React from 'react'
import Link from 'next/link'

export default function Footer() {
    return (
        <>
            <footer className="techwave_fn_footer">
                <div className="techwave_fn_footer_content">
                    <div className="copyright">
                        <p>2023© Frenify Team</p>
                    </div>
                    <div className="menu_items">
                        <ul>
                            <li><Link href="/terms">Terms of Service</Link></li>
                            <li><Link href="/privacy">Privacy Policy</Link></li>
                        </ul>
                    </div>
                </div>
            </footer>
        </>
    )
}
