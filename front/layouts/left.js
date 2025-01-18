"use client";
import Link from "next/link";
import { usePathname } from "next/navigation";
import { useState } from "react";

const data = [
  {
    title: "Home",
    pathname: "/",
    img: "svg/home.svg",
  },
  {
    title: "Alunos",
    img: "svg/community.svg",
    counter: 85,
    children: [
      {
        title: "Novo Aluno",
        pathname: "/student",
      },
      {
        title: "Todos os Alunos",
        pathname: "/models",
      },
    ],
  },
  /*{
    title: "Professores",
    img: "svg/bookmark.svg",
    counter: 48,
    children: [
      {
        title: "Novo Professor",
        pathname: "/professional",
      },
      {
        title: "Todos os Professores",
        pathname: "/models",
      },
    ],
  },
  {
    title: "Image Generation",
    pathname: "/image-generation",
    img: "svg/image.svg",
  },
  {
    title: "AI Chat Bot",
    pathname: "/ai-chat-bot",
    img: "svg/chat.svg",
  },
  {
    title: "Pricing",
    pathname: "/pricing",
    img: "svg/dollar.svg",
  },
  {
    title: "Documentation",
    pathname: "/documentation",
    img: "",
  },
  {
    title: "FAQ",
    pathname: "/faq",
    img: "",
  },
  {
    title: "Contact Us",
    pathname: "/contact",
    img: "svg/envelope.svg",
  },
  {
    title: "Log Out",
    pathname: "/sign-in",
    img: "svg/logout.svg",
  },*/
];

export default function Left({ activeTrueFalse, activeMobileMenu }) {
  const pathname = usePathname();
  const [openMenus, setOpenMenus] = useState({}); // Estado para controlar menus abertos

  const toggleMenu = (title) => {
    setOpenMenus((prevState) => ({
      ...prevState,
      [title]: !prevState[title],
    }));
  };

  return (
    <>
      <div className="techwave_fn_leftpanel">
        <div className="mobile_extra_closer" />
        {/* logo (left panel) */}
        <div className="leftpanel_logo">
          <Link href="/" className="fn_logo">
            <span className="full_logo">
              <img src="img/logo-desktop-full.png" alt="" className="desktop_logo" />
              <img src="img/logo-retina-full.png" alt="" className="retina_logo" />
            </span>
            <span className="short_logo">
              <img src="img/logo-desktop-mini.png" alt="" className="desktop_logo" />
              <img src="img/logo-retina-mini.png" alt="" className="retina_logo" />
            </span>
          </Link>
          <a className="fn__closer fn__icon_button desktop_closer" onClick={activeTrueFalse}>
            <img src="svg/arrow.svg" alt="" className="fn__svg" />
          </a>
          <a className="fn__closer fn__icon_button mobile_closer" onClick={activeMobileMenu}>
            <img src="svg/arrow.svg" alt="" className="fn__svg" />
          </a>
        </div>
        {/* !logo (left panel) */}
        {/* content (left panel) */}
        <div className="leftpanel_content">
          <div className="nav_group">
            <h2 className="group__title">Start Here</h2>
            <ul className="group__list">
              {data.map((item, i) => (
                <li key={i} className={`menu-item ${item.children ? "menu-item-has-children" : ""}`}>
                  <a
                    href={item.pathname || "#"}
                    className={`fn__tooltip menu__item ${item.pathname === pathname ? "active" : ""}`}
                    title={item.title}
                    onClick={item.children ? (e) => { e.preventDefault(); toggleMenu(item.title); } : undefined}
                  >
                    <span className="icon">
                      <img src={item.img} alt="" className="fn__svg" />
                    </span>
                    <span className="text">
                      {item.title}
                      {item.counter && <span className="count">{item.counter}</span>}
                    </span>
                  </a>

                  {/* Renderiza os filhos se existirem */}
                  {item.children && (
                    <ul className="sub-menu" style={{ display: openMenus[item.title] ? "block" : "none" }}>
                      {item.children.map((child, index) => (
                        <li key={index}>
                          <Link href={child.pathname}>
                            <span className={`text ${pathname === child.pathname ? "active" : ""}`}>{child.title}</span>
                          </Link>
                        </li>
                      ))}
                    </ul>
                  )}
                </li>
              ))}
            </ul>
          </div>
        </div>
        {/* !content (left panel) */}
      </div>
    </>
  );
}
