type ButtonProps=React.ButtonHTMLAttributes<HTMLButtonElement> & {
    variant?: "primary" | "secondary";
}

function Button({variant="primary",
                className="",
                children,
                ...rest}: ButtonProps){

    return (
        <button
        className={`btn btn-${variant} ${className} btn-primary`}
        {...rest}
        >
            {children}
        </button>
    )
}

export default Button;
