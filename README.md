## Enhancing Security at the Edge with Intel® SGX

[Intel® SGX Overview and Gramine Enabling Blog Post](https://community.intel.com/t5/Blogs/Products-and-Solutions/Security/Enhancing-Security-at-the-Edge-Intel-Software-Guard-Extensions/post/1563391)

[Intel® SGX Compatibility Check Blog Post](https://community.intel.com/t5/Blogs/Products-and-Solutions/Security/Enhancing-Security-at-the-Edge-Intel-Software-Guard-Extensions/post/1567552)

## Disclaimers

Gramine Library OS is licensed under LGPL-3.0. [See Gramine license details](https://gramine.readthedocs.io/en/stable/devel/contributing.html#copyrights-and-licenses)

You are solely responsible for determining if your use of Gramine requires any additional licenses. Intel is not responsible for obtaining any such licenses, nor liable for any licensing fees due, in connection with your use of Gramine.

Certain third-party software or hardware identified in this document only may be used upon securing a license directly from the third-party software or hardware owner. The identification of non-Intel software, tools, or services in this document does not constitute a sponsorship, endorsement, or warranty by Intel.

Security related notices are provided at [security.md](security.md), including policy and how to report a vulnerability.

Intel technologies' features and benefits depend on system configuration and may require enabled hardware, software or service activation. Performance varies depending on system configuration. No computer system can be absolutely secure.

### Intel® SGX reference sample

This reference sample provides a starting point for porting a native application deployed via container technology into Intel® SGX protected enclave. It does not provide a ready to use production level defined workload for final application. When creating your final solution, make sure you follow the best practices for Intel® SGX and Gramine Library OS configuration, including BIOS and firmware settings, Operating System setup, workload deployment, permissions, etc.

With Gramine Library OS, the application code doesn't need to be refactored using by using Intel® SGX SDK, only adjustments in the Dockerfile and manifest creation are necessary, additionally to signing the enclave.

## Bill of materials:

Java Reference Application (src/java sub-directory):

- App.java and GlobalErrorHandler.java: Sample java application
- Dockerfile.Gramine.SGX: Multi stage build Docker file to launch the java
  application with Gramine-SGX launcher.
- Dockerfile.Native: Multi stage build Docker file to launch the java
  application natively.
- java.manifest.template: Sample of a Gramine manifest used to create the
  enclave via Gramine.

## Enclave signing private key:

In order to sign the enclave for launch, it is necessary to create a private key to be used by gramine-sgx-sign:

openssl genrsa -out enclave_private_key.pem -3 3072

Note: In the reference sample it is used a locally generated private key to demonstrated the concepts, but it is discouraged for production. Developers should follow best practices, including use of Hardware Security Modules or other techniques to limite exposure and protect private keys. The .gitignore file prevents any .pem file to be included in git commits. This needs to be generated in the local development machine.

## Gramine configuration:

This reference sample uses Gramine Library OS to wrap an existing Docker container image in a workload that can be launched in Intel(r) SGX enclave.
A sample Gramine Manifest is provided at:

- [Java Sample Manifest](sample/java/java.manifest.template)

The details of the manifest configuration can be found in the official documentation at [Gramine Manifest Syntax](https://gramine.readthedocs.io/en/stable/manifest-syntax.html)
Developers should familiarize themselves with all the manifest syntax options and pay special attention to items such as sgx.trusted_files.
If applications use shared libraries required by the environment to be successfully loaded or other necessary files or directories are not listed in the sgx.trusted_files list, the application may fail to execute.

From Gramine documentation:

"Marking files as trusted is especially useful for shared libraries: a trusted library cannot be silently replaced by a malicious host because the hash verification will fail."

For performance and profiling references visit [Gramine Library OS Performance documentation page](https://gramine.readthedocs.io/en/stable/performance.html)

## References

- [Gramine Library OS Documentation](https://gramine.readthedocs.io/en/stable/)
- [Gramine Library OS GitHub Repository](https://github.com/gramineproject/gramine)
