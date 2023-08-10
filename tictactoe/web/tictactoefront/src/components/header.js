import React, {useState} from 'react'

function header() {
  return (
    <>
    <nav className="header">
        <div className="header-container">
            <Link to="/" ckassName="header-logo">
                TRVL
            </Link>
        </div>
    </nav>
    </>
  )
}

export default header